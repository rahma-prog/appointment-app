package com.example.appointment.services;

import com.example.appointment.entities.Agency;
import com.example.appointment.entities.Address;
import com.example.appointment.entities.AgencySchedule;
import com.example.appointment.entities.Staff;
import com.example.appointment.repositories.AgencyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AgencyService {

    @Autowired
    private AgencyRepository agencyRepository;

    public Page<Agency> getAllAgencies(Pageable pageable) {
        return agencyRepository.findAll(pageable);
    }

    public Agency createAgency(@Valid Agency agency) {

        if (agencyRepository.existsByNameIgnoreCase(agency.getName())) {
            throw new IllegalArgumentException("Agency with name '" + agency.getName() + "' already exists");
        }

        if (agencyRepository.existsByEmailIgnoreCase(agency.getEmail())) {
            throw new IllegalArgumentException("Email '" + agency.getEmail() + "' is already in use");
        }


        if (agencyRepository.existsByPhone(agency.getPhone())) {
            throw new IllegalArgumentException("Phone '" + agency.getPhone() + "' is already in use");
        }


        if (agency.getAddresses() != null) {
            for (Address address : agency.getAddresses()) {
                address.setAgency(agency);
            }
        }

        if (agency.getSchedules() != null) {
            for (AgencySchedule schedule : agency.getSchedules()) {
                schedule.setAgency(agency);
            }
        }

        if (agency.getStaffMembers() != null) {
            for (Staff person : agency.getStaffMembers()) {
                person.setAgency(agency);
            }
        }

        return agencyRepository.save(agency);
    }

    public Agency getAgencyById(Long id) {
        return agencyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agency not found with id: " + id));
    }

    public void deleteAgency(Long id) {
        if (!agencyRepository.existsById(id)) {
            throw new IllegalArgumentException("Agency not found with id: " + id);
        }
        agencyRepository.deleteById(id);
    }
}
