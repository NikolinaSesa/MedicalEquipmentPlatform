package com.example.MedicalEquipmentPlatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MedicalEquipmentPlatform.model.dto.AppointmentDTO;
import com.example.MedicalEquipmentPlatform.service.AppointmentService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(value = "/{companyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppointmentDTO>> getAvailableAppointments(@PathVariable Long companyId) {
        List<AppointmentDTO> appointmentDTOs = appointmentService.findAvailableAppointments(companyId);
        return new ResponseEntity<>(appointmentDTOs, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PutMapping(value = "/")
    public ResponseEntity<AppointmentDTO> reserveAppointment(@RequestBody AppointmentDTO appointmentDTO){
        if(appointmentService.reserveAppointment(appointmentDTO) == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(appointmentService.reserveAppointment(appointmentDTO), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(value = "/reservedAppointments/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppointmentDTO>> getReservedAppointmentsByUser(@PathVariable Long userId){
        return new ResponseEntity<>(appointmentService.findReservedAppointmentsByUser(userId), HttpStatus.OK);
    }
}
