package com.example.MedicalEquipmentPlatform.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {

    private Long id;
    private String address;
    private String city;
    private String zipCode;
    private String country;

    public AddressDTO(){}

    public AddressDTO(Long id, String address, String city,  String zipCode, String country){
        this.id = id;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }
    
}
