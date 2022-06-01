package com.br.clinicregistersystem.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity(name = "personAddress")
public class PersonAddress {

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank
    @Column(name = "address_street")
    private String street;

    @NotNull
    @Column(name = "address_number")
    private String number;

    @NotBlank
    @Column(name = "address_district")
    private String district;

    @Column(name = "address_complement")
    private String complement;

    @NotBlank
    @Column(name = "address_state")
    private FederativeUnits state;

    @NotBlank
    @Column(name = "address_city")
    private String city;

    @NotNull
    @Size(min = 8, max = 8)
    @Column(name = "address_postal_code")
    private Integer postalCode;

    public PersonAddress(String street, String number, String district, String complement, FederativeUnits state,
                         String city, Integer postalCode) {
        this.street = street;
        this.number = number;
        this.district = district;
        this.complement = complement;
        this.state = state;
        this.city = city;
        this.postalCode = postalCode;
    }

}
