package com.br.clinicregistersystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@PrimaryKeyJoinColumn(name = "personId")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class PersonPacient extends Person {

    @OneToOne(cascade=CascadeType.PERSIST)
    private PersonPacientChild pacientChild;

    @OneToOne(cascade=CascadeType.PERSIST)
    private PersonPacientHealthInsurance healthInsurance;

    @OneToMany(mappedBy = "prontuaryId", cascade = CascadeType.PERSIST)
    private List<PersonPacientProntuary> prontuaries = new ArrayList<>();

}
