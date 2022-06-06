package com.br.clinicregistersystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@PrimaryKeyJoinColumn(name = "personId")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class PersonPacient extends Person {

    @OneToOne(cascade=CascadeType.PERSIST)
    private PersonPacientChild pacientChild;

    @OneToOne(cascade=CascadeType.PERSIST)
    private PersonPacientHealthInsurance healthInsurance;

    @OneToOne(cascade=CascadeType.PERSIST)
    private PacientProntuary prontuary;

}
