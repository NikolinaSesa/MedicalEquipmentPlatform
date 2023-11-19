package com.example.MedicalEquipmentPlatform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MedicalEquipmentPlatform.model.RegularUser;
import com.example.MedicalEquipmentPlatform.model.User;
import com.example.MedicalEquipmentPlatform.model.dto.RegularUserDTO;
import com.example.MedicalEquipmentPlatform.service.RegularUserService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/user")
public class RegularUserController {

    private final RegularUserService regularUserService;

    @Autowired
    public RegularUserController(RegularUserService regularUserService){
        this.regularUserService = regularUserService;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegularUserDTO> register(@RequestBody RegularUserDTO regularUserDTO){
        RegularUser regularUser = regularUserService.register(regularUserDTO);

        if(regularUser == null) {

            return new ResponseEntity<>(HttpStatus.valueOf(409));

        }
        RegularUserDTO newRegularUserDTO = new RegularUserDTO(regularUser.getId(), regularUser.getEmail(), regularUser.getPassword(), regularUser.getFirstName(), regularUser.getLastName(), regularUser.getCity(), regularUser.getCountry(), regularUser.getPhoneNumber(), regularUser.getRole(), regularUser.getProfession(), regularUser.getCompanyInformation());

        return new ResponseEntity<>(newRegularUserDTO, HttpStatus.OK);
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {
        User user = regularUserService.confirmEmail(confirmationToken);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok("Email verified successfully.");
    }

}
