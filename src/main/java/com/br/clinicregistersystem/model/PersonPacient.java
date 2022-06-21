package com.br.clinicregistersystem.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@PrimaryKeyJoinColumn(name = "personId")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class PersonPacient extends Person {

    @OneToMany(mappedBy = "childId", cascade = CascadeType.ALL)
    private List<PersonPacientChild> pacientChildren = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    @JoinColumn(name = "healthInsuranceId")
    private PersonPacientHealthInsurance healthInsurance;

    @OneToMany(mappedBy = "prontuaryId", cascade = CascadeType.ALL)
    private List<PersonPacientProntuary> prontuaries = new ArrayList<>();

    @Column
    private String pacientName;

}
