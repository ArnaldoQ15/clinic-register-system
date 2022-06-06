package com.br.clinicregistersystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDate;

@PrimaryKeyJoinColumn(name = "personId")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class PersonDoctor extends Person {

    @Column
    private Integer professionalRegisterNumber;

    @Column
    private FederativeUnits professionalRegisterState;

    @Column
    private LocalDate professionalRegisterValidity;

    @Column
    private MedicalEspeciality medicalEspeciality;

}
