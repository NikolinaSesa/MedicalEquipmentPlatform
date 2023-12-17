package com.example.MedicalEquipmentPlatform.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservedEquipmentDTO {

    private Long id;
    private EquipmentDTO equipmentDTO;
    private Integer quantity;
}
