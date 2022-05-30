package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity(name = "pacientProntuary")
public class PersonPacientProntuary extends PersonPacient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pacient_prontuary_id")
    private Long prontuaryId;

    @Column(name = "pacient_first_time")
    private Boolean firstTime;

    @NotBlank
    @Column(name = "pacient_symptoms")
    private String symptoms;

    @OneToOne
    private PersonPacient personPacient;


    public PersonPacientProntuary(String name, Integer age, String email, PersonSex sex, PersonPhone phone, Integer documentCpf, Integer documentRg, PersonAddress address) {
        super(name, age, email, sex, phone, documentCpf, documentRg, address);
    }

//    public void seeLastConsult() {
//        if (this.firstTime) {
//            this.lastConsult = null;
//        } else {
//            this.lastConsult = lastConsult;
//        }
//    }
}
