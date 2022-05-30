package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity(name = "personPacient")
public class PersonPacient extends Person {

    @NotNull
    @Id
    @Column(name = "pacient_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pacientId;

    @OneToOne
    private PersonPacientChild pacientChild;

    @OneToOne
    private PersonPacientHealthInsurance healthInsurance;

    @NotBlank
    private PersonPacientProntuary prontuary;

    public PersonPacient(String name, Integer age, String email, PersonSex sex, PersonPhone phone, Integer documentCpf, Integer documentRg, PersonAddress address) {
        super(name, age, email, sex, phone, documentCpf, documentRg, address);
    }

}
