package com.example.MedicalEquipmentPlatform.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegularUserDTO {

    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String city;
    private String country;
    private String phoneNumber;
    private String role;
    private String profession;
    private String companyInformation;
    
    public RegularUserDTO(){}

    public RegularUserDTO(Long id, String email, String password, String firstName, String lastName, String city, String country, String phoneNumber, String role, String profession, String companyInformation){
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.profession = profession;
        this.companyInformation = companyInformation;
    }
}
