package com.br.clinicregistersystem.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "pacientChild")
public class PacientChild {

    @Id
    @Column(name = "pacient_child_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long childId;

    @Column(name = "pacient_child_name")
    private String childName;

    @Column(name = "pacient_child_age")
    private Integer childAge;

    @Column(name = "pacient_child_sex")
    private PersonSex childSex;

    @Column(name = "pacient_child_printed_term")
    private Boolean printedTerm;

    public PacientChild(String childName, Integer childAge, PersonSex childSex, Boolean printedTerm) {
        this.childName = childName;
        this.childAge = childAge;
        this.childSex = childSex;
        this.printedTerm = false;
    }

}
