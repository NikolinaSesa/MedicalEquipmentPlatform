package com.example.MedicalEquipmentPlatform.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDTO {
    
    private Long id;
    private String companyName;
    private String description;
    private Double averageRating;
    private AddressDTO addressDTO;
    private List<EquipmentDTO> equipmentDTOs;

    public CompanyDTO(){}

    public CompanyDTO(Long id, String companyName, String description, Double averageRating, AddressDTO addressDTO, List<EquipmentDTO> equipmentDTOs){
        this.id = id;
        this.companyName = companyName;
        this.description = description;
        this.averageRating = averageRating;
        this.addressDTO = addressDTO;
        this.equipmentDTOs = equipmentDTOs;
    }
}
