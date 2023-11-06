package com.example.MedicalEquipmentPlatform.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String name;

    @Column
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "companyId", nullable = false)
    private Company company;

    public Equipment(){}

    public Equipment(String name, Long quantity, Company company){
        this.name = name;
        this.quantity = quantity;
        this.company = company;
    }

    public Equipment(Long id, String name, Long quantity, Company company){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.company = company;
    }
}

