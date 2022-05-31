package com.br.clinicregistersystem.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity(name = "pacientProntuary")
public class PersonPacientProntuary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pacient_prontuary_id")
    private Long prontuaryId;

    @Column(name = "pacient_first_time")
    private Boolean firstTime;

    @NotBlank
    @Column(name = "pacient_symptoms")
    private String symptoms;

    @Transient
    @OneToOne
    private Pacient personPacient;

    public PersonPacientProntuary(Boolean firstTime, String symptoms) {
        this.firstTime = true;
        this.symptoms = symptoms;
    }

    public void setPersonPacient(Pacient personPacient) {
    }
}
