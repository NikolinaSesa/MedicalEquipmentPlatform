package com.example.MedicalEquipmentPlatform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "system_admins")
@PrimaryKeyJoinColumn(name = "user_id")
public class SystemAdmin extends User{
    
    public SystemAdmin(){
        super();
    }

    public SystemAdmin(String email, String password, String firstName, String lastName, String city, String country, String phoneNumber, String role){
        super(email, password, firstName, lastName, city, country, phoneNumber, role);

    }

    public SystemAdmin(Long id, String email, String password, String firstName, String lastName, String city, String country, String phoneNumber, String role){
        super(id, email, password, firstName, lastName, city, country, phoneNumber, role);

    }
}
