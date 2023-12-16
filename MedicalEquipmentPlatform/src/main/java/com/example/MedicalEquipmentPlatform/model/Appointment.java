package com.example.MedicalEquipmentPlatform.model;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate date;

    @Column
    private Duration duration;

    @ManyToOne
    @JoinColumn(name = "companyAdminId", nullable = false)
    private CompanyAdmin companyAdmin;

    @ManyToOne
    @JoinColumn(name = "regularUserId")
    private RegularUser regularUser;

    @OneToMany(mappedBy = "appointment")
    private List<ReservedEquipment> reservedEquipments;

}
