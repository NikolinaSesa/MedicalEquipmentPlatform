package com.example.MedicalEquipmentPlatform.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String city;
    private String country;
    private String phoneNumber;
    private String profession;
    private String companyInformation;
    
    public UserDTO(){}

    public UserDTO(Long id, String email, String password, String firstName, String lastName, String city, String country, String phoneNumber, String profession, String companyInformation){
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.profession = profession;
        this.companyInformation = companyInformation;
    }
}
