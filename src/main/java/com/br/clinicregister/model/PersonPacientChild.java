package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "pacientChild")
public class PersonPacientChild {

    @Id
    @Column(name = "pacient_child_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long childId;

    @Column(name = "pacient_child_name")
    private String name;

    @Column(name = "pacient_child_age")
    private Integer age;

    @Column(name = "pacient_child_sex")
    private PersonSex sex;

    @Column(name = "pacient_child_printed_term")
    private Boolean printedTerm;

}
