package com.example.MedicalEquipmentPlatform.model.dto;

import java.sql.Date;
import java.time.Duration;

import com.example.MedicalEquipmentPlatform.model.CompanyAdmin;

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
    private CompanyAdmin companyAdmin;
    private Date date;
    private Duration duration;
}
