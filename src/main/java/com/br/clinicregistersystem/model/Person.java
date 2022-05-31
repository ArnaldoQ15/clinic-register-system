package com.br.clinicregistersystem.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "person")
public abstract class Person {

    @NotNull
    @EqualsAndHashCode.Include
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long personId;

    @NotBlank
    @Column(name = "person_name")
    protected String personName;

    @NotNull
    @Column(name = "person_age")
    protected Integer personAge;

    @NotBlank
    @Column(name = "person_email")
    @Email
    protected String personEmail;

    @NotNull
    @Column(name = "person_sex")
    protected PersonSex personSex;

    @NotNull
    @Column(name = "person_phone")
    protected PersonPhone personPhone;

    @NotNull
    @Column(name = "person_document_cpf")
    protected Long personDocumentCpf;

    @Column(name = "person_document_rg")
    protected Long personDocumentRg;

    @NotNull
    @Column(name = "person_address")
    protected PersonAddress personAddress;

    @NotNull
    @Column(name = "person_register_date")
    protected OffsetDateTime personRegisterDate;

    public Person(String personName, Integer personAge, String personEmail, PersonSex personSex,
                  PersonPhone personPhone, Long personDocumentCpf, Long personDocumentRg,
                  PersonAddress personAddress) {
        this.personName = personName;
        this.personAge = personAge;
        this.personEmail = personEmail;
        this.personSex = personSex;
        this.personPhone = personPhone;
        this.personDocumentCpf = personDocumentCpf;
        this.personDocumentRg = personDocumentRg;
        this.personAddress = personAddress;
    }
}
