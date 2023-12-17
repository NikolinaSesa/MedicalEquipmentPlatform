package com.example.MedicalEquipmentPlatform.model.dto;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
    private Long id;
    private CompanyAdminDTO companyAdminDTO;
    private LocalDate date;
    private Duration duration;
    private RegularUserDTO regularUserDTO;
    private List<ReservedEquipmentDTO> reservedEquipmentDTOs;

}
