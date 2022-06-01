package com.br.clinicregistersystem.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity(name = "personPacient")
public class Pacient extends Person {

    @NotNull
    @Column(name = "pacient_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pacientId;

    @OneToOne
    private PacientChild pacientChild;

    @OneToOne
    private PacientHealthInsurance healthInsurance;

    @OneToOne
    @NotBlank
    private PersonPacientProntuary prontuary;

    public Pacient(String personName, Integer personAge, String personEmail, PersonSex personSex, List<PersonPhone> personPhone, LocalDate personBirthday, Long personDocumentCpf, Long personDocumentRg, List<PersonAddress> personAddresses, PacientChild pacientChild, PacientHealthInsurance healthInsurance, PersonPacientProntuary prontuary) {
        super(personName, personAge, personEmail, personSex, personPhone, personBirthday, personDocumentCpf, personDocumentRg, personAddresses);
        this.pacientChild = pacientChild;
        this.healthInsurance = healthInsurance;
        this.prontuary = prontuary;
    }


    public void makeBirthday(LocalDate personBirthday) {
        Integer actualAge = getPersonAge();
        int month = personBirthday.getMonthValue();
        int day = personBirthday.getDayOfMonth();
        int thatMonth = LocalDate.now().getMonthValue();
        int thatDay = LocalDate.now().getDayOfMonth();

        if ((day == thatDay) && (month == thatMonth)) {
            actualAge = actualAge + 1;
        }

        this.setPersonAge(actualAge);
    }


}
