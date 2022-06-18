package com.br.clinicregistersystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@PrimaryKeyJoinColumn(name = "personId")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class PersonPacient extends Person {

    @OneToMany(mappedBy = "childId", cascade = CascadeType.ALL)
    private List<PersonPacientChild> pacientChildren = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    private PersonPacientHealthInsurance healthInsurance;

    @OneToMany(mappedBy = "prontuaryId", cascade = CascadeType.ALL)
    private List<PersonPacientProntuary> prontuaries = new ArrayList<>();

}
