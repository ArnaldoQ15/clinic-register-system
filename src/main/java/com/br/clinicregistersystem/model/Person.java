package com.br.clinicregistersystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.InheritanceType.JOINED;

@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy=JOINED)
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public abstract class Person {

    @EqualsAndHashCode.Include
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long personId;

    @Column
    protected String personName;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column
    protected Integer personAge;

    @Column
    @Email
    protected String personEmail;

    @Column
    protected PersonSex personSex;

    @OneToMany(mappedBy = "phoneId", cascade = CascadeType.ALL)
    protected List<PersonPhone> personPhones = new ArrayList<>();

    @Column
    protected LocalDate personBirthday;

    @CPF(message = "CPF's wrong paremeter.")
    @Column
    protected String personDocumentCpf;

    @Column
    protected String personDocumentRg;

    @OneToMany(mappedBy = "addressId", cascade = CascadeType.ALL)
    protected List<PersonAddress> personAddresses = new ArrayList<>();

    @Column
    protected OffsetDateTime personLastRegisterDate;

    @Column
    protected Boolean personStatus = true;

    public Person(String personName, String personEmail, PersonSex personSex,
                  List<PersonPhone> personPhone, LocalDate personBirthday, String personDocumentCpf, String personDocumentRg,
                  List<PersonAddress> personAddresses) {
        this.personName = personName;
        this.personEmail = personEmail;
        this.personSex = personSex;
        this.personPhones = personPhone;
        this.personBirthday = personBirthday;
        this.personDocumentCpf = personDocumentCpf;
        this.personDocumentRg = personDocumentRg;
        this.personAddresses = personAddresses;
    }

}
