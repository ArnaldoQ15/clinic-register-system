package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

@Getter
@Setter
@Table(name = "all_person")
public abstract class Person {

    @NotNull
    @Id
    @Column(name = "person_id")
    private Long id;

    @NotNull
    @Column(name = "person_name")
    @Size(max = 80)
    protected String name;

    @NotNull
    @Column(name = "person_age")
    protected Integer age;

    @NotNull
    @Column(name = "person_email")
    @Email
    protected String email;

    @NotNull
    @Column(name = "person_sex")
    @Enumerated(EnumType.STRING)
    protected PersonSex sex;

    @NotNull
    @Column(name = "person_phone")
    @Size(max = 14)
    protected PersonPhone phone;

    @NotNull
    @Column(name = "person_register_date")
    protected OffsetDateTime register_date;

}
