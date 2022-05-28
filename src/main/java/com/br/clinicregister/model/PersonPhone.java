package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity(name = "personPhone")
public class PersonPhone {

    @Id
    @Column(name = "phone_id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phoneId;

    @NotBlank
    @Column(name = "phone_type")
    private PersonPhoneType type;

    @NotNull
    @Column(name = "phone_number")
    private Integer number;

    @Column(name = "phone_name")
    @Size(max = 80)
    protected String personPhoneName;

    public PersonPhone(PersonPhoneType type, Integer number, String personPhoneName) {
        this.type = type;
        this.number = number;
        this.personPhoneName = personPhoneName;
    }
}
