package com.example.MedicalEquipmentPlatform.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompanyAdminDTO {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String city;
    private String country;
    private String phoneNumber;
    private String role;
    private CompanyDTO companyDTO;

    public CompanyAdminDTO(Long id, String email, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
