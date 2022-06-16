package com.br.clinicregistersystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@PrimaryKeyJoinColumn(name = "personId")
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDoctor extends Person {

    @Column
    private Integer professionalRegisterNumber;

    @Column
    @Enumerated(EnumType.STRING)
    private FederativeUnits professionalRegisterState;

    @Column
    private LocalDate professionalRegisterValidity;

    @Column
    @Enumerated(EnumType.STRING)
    private MedicalEspeciality medicalEspeciality;

}
