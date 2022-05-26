package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Getter
@Setter
@Table(name = "person_pacient_child")
public class PersonPacientChild {

    @Id
    @Column(name = "pacient_child_id")
    private Long id;

    @Size(max = 80)
    @Column(name = "pacient_child_name")
    private String name;

    @Column(name = "pacient_child_age")
    private Integer age;

    @Column(name = "pacient_child_sex")
    private PersonSex sex;

    @Column(name = "pacient_child_printed_term")
    private Boolean printedTerm;

}
