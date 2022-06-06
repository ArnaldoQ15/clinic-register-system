package com.br.clinicregistersystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@PrimaryKeyJoinColumn(name = "personId")
@NoArgsConstructor
@Data
@Entity(name = "personPacient")
public class Pacient extends Person {

    @OneToOne(cascade=CascadeType.PERSIST)
    private PacientChild pacientChild;

    @OneToOne(cascade=CascadeType.PERSIST)
    private PacientHealthInsurance healthInsurance;

    @OneToOne(cascade=CascadeType.PERSIST)
    private PersonPacientProntuary prontuary;

    public Pacient(String personName, String personEmail, PersonSex personSex, List<PersonPhone> personPhones, LocalDate personBirthday, String personDocumentCpf, String personDocumentRg, List<PersonAddress> personAddresses, PacientChild pacientChild, PacientHealthInsurance healthInsurance, PersonPacientProntuary prontuary) {
        super(personName, personEmail, personSex, personPhones, personBirthday, personDocumentCpf, personDocumentRg, personAddresses);
        this.pacientChild = pacientChild;
        this.healthInsurance = healthInsurance;
        this.prontuary = prontuary;
    }

}
