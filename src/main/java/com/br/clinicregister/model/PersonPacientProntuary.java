package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Table(name = "person_pacient_prontuary")
public class PersonPacientProntuary extends PersonPacient {

    @Column(name = "pacient_first_time")
    private Boolean firstTime;

    @Column(name = "pacient_next_consult")
    private PersonPacientNextConsult nextConsult;

    @Column(name = "pacient_last_consult")
    private PersonPacientLastConsult lastConsult;

    @NotBlank
    @Column(name = "pacient_symptoms")
    private String symptoms;

    public void setLastConsult(PersonPacientLastConsult lastConsult) {
        if (this.firstTime) {
            this.lastConsult = null;
        } else {
            this.lastConsult = lastConsult;
        }
    }
}
