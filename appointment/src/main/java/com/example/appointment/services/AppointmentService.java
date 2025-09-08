package com.example.appointment.services;

import com.example.appointment.entities.Agency;
import com.example.appointment.entities.Appointment;
import com.example.appointment.entities.Staff;
import com.example.appointment.entities.User;
import com.example.appointment.repositories.AgencyRepository;
import com.example.appointment.repositories.AppointmentRepository;
import com.example.appointment.repositories.StaffRepository;
import com.example.appointment.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private StaffRepository staffRepository;

    public Page<Appointment> getAllAppointments(Pageable pageable) {
        return appointmentRepository.findAll(pageable);
    }

    public Appointment createAppointment(@Valid Appointment appointment) {
        // Validate and fetch full entities
        if (appointment.getUser() == null || appointment.getUser().getId() == null) {
            throw new IllegalArgumentException("User is required");
        }
        User user = userRepository.findById(appointment.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (appointment.getAgency() == null || appointment.getAgency().getId() == null) {
            throw new IllegalArgumentException("Agency is required");
        }
        Agency agency = agencyRepository.findById(appointment.getAgency().getId())
                .orElseThrow(() -> new IllegalArgumentException("Agency not found"));

        if (appointment.getStaff() == null || appointment.getStaff().getId() == null) {
            throw new IllegalArgumentException("Staff is required");
        }
        Staff staff = staffRepository.findById(appointment.getStaff().getId())
                .orElseThrow(() -> new IllegalArgumentException("Staff not found"));

        // ✅ Check if user already has an appointment on the same day
        LocalDateTime appointDate = appointment.getAppointDate();
        LocalDateTime startOfDay = appointDate.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = appointDate.toLocalDate().atTime(23, 59, 59);

        boolean exists = appointmentRepository.existsByUserIdAndAppointDateBetween(
                user.getId(), startOfDay, endOfDay
        );

        if (exists) {
            throw new IllegalArgumentException("User already has an appointment on this date");
        }

        // Set the full objects
        appointment.setUser(user);
        appointment.setAgency(agency);
        appointment.setStaff(staff);
        appointment.setStaffJob(staff.getJobTitle());

        return appointmentRepository.save(appointment);
    }

    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));
    }

    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new IllegalArgumentException("Appointment not found");
        }
        appointmentRepository.deleteById(id);
    }
}

