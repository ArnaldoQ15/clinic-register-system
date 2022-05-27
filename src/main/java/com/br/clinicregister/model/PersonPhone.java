package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity(name = "personPhone")
public class PersonPhone {

    @NotBlank
    @Column(name = "person_phone_type")
    protected PersonPhoneType type;

    @NotNull
    @Column(name = "person_phone_number")
    protected Integer number;

    @Column(name = "person_phone_name")
    @Size(max = 80)
    protected String personPhoneName;

    public PersonPhone(PersonPhoneType type, Integer number, String personPhoneName) {
        this.type = type;
        this.number = number;
        this.personPhoneName = personPhoneName;
    }
}
