package com.example.MedicalEquipmentPlatform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "company_admins")
@PrimaryKeyJoinColumn(name = "user_id")
public class CompanyAdmin extends User {

    @ManyToOne
    @JoinColumn(name = "companyId", nullable = false)
    private Company company;

    public CompanyAdmin(){
        super();
    }

    public CompanyAdmin(String email, String password, String firstName, String lastName, String city, String country, String phoneNumber, String role, Company company){
        super(email, password, firstName, lastName, city, country, phoneNumber, role);
        this.company = company;
    }

    public CompanyAdmin(Long id, String email, String password, String firstName, String lastName, String city, String country, String phoneNumber, String role, Company company){
        super(id, email, password, firstName, lastName, city, country, phoneNumber, role);
        this.company = company;
    }
    
}
