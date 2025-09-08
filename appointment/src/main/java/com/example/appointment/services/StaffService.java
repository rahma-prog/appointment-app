package com.example.appointment.services;

import com.example.appointment.entities.Staff;
import com.example.appointment.entities.Agency;
import com.example.appointment.repositories.StaffRepository;
import com.example.appointment.repositories.AgencyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private AgencyRepository agencyRepository;


    public Page<Staff> getAllStaff(Pageable pageable) {
        return staffRepository.findAll(pageable);
    }


    public Staff getStaffById(Long id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Staff not found with id: " + id));
    }


    public Staff createStaff(@Valid Staff staff) {

        if (staff.getAgency() == null || staff.getAgency().getId() == null) {
            throw new IllegalArgumentException("Agency is required");
        }

        Agency agency = agencyRepository.findById(staff.getAgency().getId())
                .orElseThrow(() -> new IllegalArgumentException("Agency not found with id: " + staff.getAgency().getId()));


        if (staffRepository.existsByEmailIgnoreCaseAndAgency(staff.getEmail(), agency)) {
            throw new IllegalArgumentException("Email '" + staff.getEmail() + "' is already in use in this agency");
        }

        staff.setAgency(agency);
        return staffRepository.save(staff);
    }


    public Staff updateStaff(Long id, @Valid Staff updatedStaff) {
        Staff existing = getStaffById(id);

        if (updatedStaff.getFirstName() != null) existing.setFirstName(updatedStaff.getFirstName());
        if (updatedStaff.getLastName() != null) existing.setLastName(updatedStaff.getLastName());
        if (updatedStaff.getEmail() != null) {

            if (!existing.getEmail().equalsIgnoreCase(updatedStaff.getEmail()) &&
                    staffRepository.existsByEmailIgnoreCaseAndAgency(updatedStaff.getEmail(), existing.getAgency())) {
                throw new IllegalArgumentException("Email '" + updatedStaff.getEmail() + "' is already in use in this agency");
            }
            existing.setEmail(updatedStaff.getEmail());
        }
        if (updatedStaff.getJobTitle() != null) existing.setJobTitle(updatedStaff.getJobTitle());

        return staffRepository.save(existing);
    }


    public void deleteStaff(Long id) {
        if (!staffRepository.existsById(id)) {
            throw new IllegalArgumentException("Staff not found with id: " + id);
        }
        staffRepository.deleteById(id);
    }
}
