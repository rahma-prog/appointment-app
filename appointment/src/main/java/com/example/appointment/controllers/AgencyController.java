package com.example.appointment.controllers;

import com.example.appointment.entities.Agency;
import com.example.appointment.services.AgencyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/agencies")
@Validated
public class AgencyController {

    @Autowired
    private AgencyService agencyService;

    @GetMapping
    public Page<Agency> getAllAgencies(@PageableDefault(size = 10) Pageable pageable) {
        return agencyService.getAllAgencies(pageable);
    }

    @PostMapping
    public ResponseEntity<Agency> createAgency(@Valid @RequestBody Agency agency) {
        Agency saved = agencyService.createAgency(agency);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public Agency getAgency(@PathVariable Long id) {
        return agencyService.getAgencyById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAgency(@PathVariable Long id) {
        agencyService.deleteAgency(id);
    }
}
