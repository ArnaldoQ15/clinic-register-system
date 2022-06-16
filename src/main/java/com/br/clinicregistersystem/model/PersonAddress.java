package com.br.clinicregistersystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class PersonAddress {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column
    private String street;

    @Column
    private String number;

    @Column
    private String district;

    @Column
    private String complement;

    @Column
    private FederativeUnits state;

    @Column
    private String city;

    @Column
    private Integer postalCode;

//    @ManyToMany
//    @JoinColumn(name = "fkPersonId")
//    private Person person;

}
