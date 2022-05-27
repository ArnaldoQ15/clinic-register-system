package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity(name = "personPacient")
public class PersonPacient extends Person {

    @NotNull
    @Id
    @Column(name = "pacient_id")
    private Long id;

    @Column(name = "pacient_child_data")
    private PersonPacientChild pacientChild;

    @Column(name = "pacient_health_insurance")
    private PersonPacientHealthInsurance healthInsurance;

    @NotBlank
    @Column(name = "pacient_prontuary")
    private PersonPacientProntuary prontuary;

    public PersonPacient(String name, Integer age, String email, PersonSex sex, PersonPhone phone, Integer documentCpf, Integer documentRg, PersonAddress address) {
        super(name, age, email, sex, phone, documentCpf, documentRg, address);
    }

//    public void markConsult() {
//        PersonPacient pacient =
//    }

}
