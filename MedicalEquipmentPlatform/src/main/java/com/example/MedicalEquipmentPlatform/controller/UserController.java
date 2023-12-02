package com.example.MedicalEquipmentPlatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MedicalEquipmentPlatform.model.AuthRequest;
import com.example.MedicalEquipmentPlatform.model.User;
import com.example.MedicalEquipmentPlatform.model.dto.UserDTO;
import com.example.MedicalEquipmentPlatform.service.JwtService;
import com.example.MedicalEquipmentPlatform.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired 
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @PostMapping("/generateToken") 
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) { 
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())); 
        if (authentication.isAuthenticated()) { 
            return jwtService.generateToken(authRequest.getEmail()); 
        } else { 
            throw new UsernameNotFoundException("invalid user request !"); 
        } 
    }

    @GetMapping(value = "/getUser/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ROLE_SYSTEM_ADMIN')" +
            "|| hasAuthority('ROLE_COMPANY_ADMIN')" +
            "|| hasAuthority('ROLE_USER')")
    public ResponseEntity<UserDTO> getUser(@PathVariable String email){
        User user = userService.findByEmail(email);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            UserDTO userDTO = new UserDTO(user.getId(), user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getCity(), user.getCountry(), user.getPhoneNumber(), user.getRole());
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
    }

}
