package com.br.clinicregistersystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class PersonPacientChild {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long childId;

    @Column
    private String childName;

    @Column
    private Integer childAge;

    @Column
    private PersonSex childSex;

    @Column
    private Boolean printedTerm = false;

}
