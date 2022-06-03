package com.br.clinicregistersystem.model;

import com.br.clinicregistersystem.exception.ValidationGroups;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.InheritanceType.JOINED;

@NoArgsConstructor
@Inheritance(strategy=JOINED)
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public abstract class Person {

    @NotNull(groups = ValidationGroups.PersonId.class)
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
    @OneToMany(mappedBy = "phoneId", cascade = CascadeType.ALL)
    protected List<PersonPhone> personPhones = new ArrayList<>();

    @NotNull
    @Column(name = "person_birthday")
    protected LocalDate personBirthday;

    @NotNull
    @Column(name = "person_document_cpf")
    protected Long personDocumentCpf;

    @Column(name = "person_document_rg")
    protected Long personDocumentRg;

    @OneToMany(mappedBy = "addressId", cascade = CascadeType.ALL)
    protected List<PersonAddress> personAddresses = new ArrayList<>();

    @NotNull
    @Column(name = "person_last_register_date")
    protected OffsetDateTime personLastRegisterDate;

    @NotNull
    @Column(name = "person_status")
    protected Boolean personStatus;

    public Person(String personName, Integer personAge, String personEmail, PersonSex personSex,
                  List<PersonPhone> personPhone, LocalDate personBirthday, Long personDocumentCpf, Long personDocumentRg,
                  List<PersonAddress> personAddresses, Boolean personStatus) {
        this.personName = personName;
        this.personAge = personAge;
        this.personEmail = personEmail;
        this.personSex = personSex;
        this.personPhones = personPhone;
        this.personBirthday = personBirthday;
        this.personDocumentCpf = personDocumentCpf;
        this.personDocumentRg = personDocumentRg;
        this.personAddresses = personAddresses;
        this.personStatus = true;
    }

    public List<PersonAddress> getPersonAddresses() {
        List<PersonAddress> newAddress = new ArrayList<>();

        for (PersonAddress locale : personAddresses) {
            if (personAddresses != null)
                newAddress.addAll(personAddresses);
        }
        newAddress = personAddresses;

        return personAddresses;
    }

    public List<PersonPhone> getPersonPhones() {
        List<PersonPhone> newPhone = new ArrayList<>();

        for (PersonPhone register : personPhones) {
            if (personPhones.contains(PersonPhone.class))
                newPhone.addAll(personPhones);
        }
        newPhone = personPhones;

        return personPhones;
    }

}
