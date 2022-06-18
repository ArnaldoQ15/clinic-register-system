package com.br.clinicregistersystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class PersonPhone {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phoneId;

    @Column
    @Enumerated(EnumType.STRING)
    private PersonPhoneType type;

    @Column
    private Long number;

    @Column
    @Size(max = 80)
    private String personPhoneName;

    @ManyToOne
    @JoinColumn(name = "personId")
    @ToString.Exclude
    private Person person;

}
