package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Table(name = "person_address")
public class PersonAddress {

    @NotBlank
    @Column(name = "person_address_street")
    private String street;

    @NotNull
    @Column(name = "person_address_number")
    private Integer number;

    @NotBlank
    @Column(name = "person_address_district")
    private String district;

    @Column(name = "person_address_complement")
    private String complement;

    @NotBlank
    @Column(name = "person_address_state")
    private FederativeUnits state;

    @NotBlank
    @Column(name = "person_address_city")
    private String city;

    @NotNull
    @Size(min = 8, max = 8)
    @Column(name = "person_address_postal_code")
    private Integer postalCode;

}
