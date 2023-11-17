package com.example.MedicalEquipmentPlatform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MedicalEquipmentPlatform.model.User;
import com.example.MedicalEquipmentPlatform.model.dto.UserDTO;
import com.example.MedicalEquipmentPlatform.service.UserService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO){
        User user = userService.create(userDTO);

        if(user == null) {

            return new ResponseEntity<>(HttpStatus.valueOf(409));

        }
        UserDTO newUserDTO = new UserDTO(user.getId(), user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getCity(), user.getCountry(), user.getPhoneNumber(), user.getProfession(), user.getCompanyInformation());

        return new ResponseEntity<>(newUserDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{email}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> findByEmailAndPassword(@PathVariable String email, @PathVariable String password){
        User user = userService.findByEmailAndPassword(email, password);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserDTO newUserDTO = new UserDTO(user.getId(), user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getCity(), user.getCountry(), user.getPhoneNumber(), user.getProfession(), user.getCompanyInformation());

        return new ResponseEntity<>(newUserDTO, HttpStatus.OK);
    }
}
