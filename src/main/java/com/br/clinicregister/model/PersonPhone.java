package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Table(name = "person_phone")
public abstract class PersonPhone {

    @NotNull
    @Column(name = "person_phone_type")
    protected PersonPhoneType type;

    @NotNull
    @Column(name = "person_phone_number")
    @Size(min = 14, max = 14)
    protected Integer number;

    @Column(name = "person_phone_name")
    @Size(max = 80)
    protected String personPhoneName;

}
