package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Table(name = "person_pacient")
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

}
