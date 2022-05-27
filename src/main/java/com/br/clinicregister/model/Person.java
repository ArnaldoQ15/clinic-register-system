package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity(name = "allPerson")
public abstract class Person {

    @NotNull
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn
    private Long id;

    @NotBlank
    @Column(name = "person_name")
    @Size(max = 80)
    @JoinColumn
    protected String name;

    @NotNull
    @Column(name = "person_age")
    protected Integer age;

    @NotBlank
    @Column(name = "person_email")
    @Email
    protected String email;

    @NotNull
    @Column(name = "person_sex")
    protected PersonSex sex;

    @NotNull
    @Column(name = "person_phone")
    @Size(max = 14)
    protected PersonPhone phone;

    @NotNull
    @Column(name = "person_document_cpf")
    @Size(min = 11, max = 11)
    protected Integer documentCpf;

    @Column(name = "person_document_rg")
    @Size(max = 10)
    protected Integer documentRg;

    @NotNull
    @Column(name = "person_address")
    protected PersonAddress address;

    @NotNull
    @Column(name = "person_register_date")
    protected OffsetDateTime register_date;

    public Person(String name, Integer age, String email, PersonSex sex, PersonPhone phone,
                  Integer documentCpf, Integer documentRg, PersonAddress address) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.sex = sex;
        this.phone = phone;
        this.documentCpf = documentCpf;
        this.documentRg = documentRg;
        this.address = address;
    }

}
