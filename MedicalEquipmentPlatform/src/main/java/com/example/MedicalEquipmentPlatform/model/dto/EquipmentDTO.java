package com.example.MedicalEquipmentPlatform.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipmentDTO {
    
    private Long id;
    private String name;
    private Long quantity;

    public EquipmentDTO(){}

    public EquipmentDTO(Long id, String name, Long quantity){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }
}
