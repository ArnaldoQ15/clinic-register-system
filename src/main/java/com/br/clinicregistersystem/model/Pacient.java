package com.br.clinicregistersystem.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity(name = "personPacient")
public class Pacient extends Person {

    @NotNull
    @Id
    @Column(name = "pacient_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pacientId;

    @OneToOne
    private PacientChild pacientChild;

    @OneToOne
    private PacientHealthInsurance healthInsurance;

    @OneToOne
    @NotBlank
    private PersonPacientProntuary prontuary;

    public Pacient(String personName, Integer personAge, String personEmail, PersonSex personSex, PersonPhone personPhone,
                   Long personDocumentCpf, Long personDocumentRg, PersonAddress personAddress, PacientChild pacientChild,
                   PacientHealthInsurance healthInsurance, PersonPacientProntuary prontuary) {
        super(personName, personAge, personEmail, personSex, personPhone, personDocumentCpf, personDocumentRg, personAddress);
        this.pacientChild = pacientChild;
        this.healthInsurance = healthInsurance;
        this.prontuary = prontuary;
    }

}
