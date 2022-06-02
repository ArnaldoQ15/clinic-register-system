package com.br.clinicregistersystem.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity(name = "personPacient")
public class Pacient extends Person {

    @OneToOne(cascade=CascadeType.PERSIST)
    private PacientChild pacientChild;

    @OneToOne(cascade=CascadeType.PERSIST)
    private PacientHealthInsurance healthInsurance;

    @OneToOne(cascade=CascadeType.PERSIST)
    @NotBlank
    private PersonPacientProntuary prontuary;

    public Pacient(String personName, Integer personAge, String personEmail, PersonSex personSex, List<PersonPhone> personPhones, LocalDate personBirthday, Long personDocumentCpf, Long personDocumentRg, List<PersonAddress> personAddresses, Boolean personStatus, PacientChild pacientChild, PacientHealthInsurance healthInsurance, PersonPacientProntuary prontuary) {
        super(personName, personAge, personEmail, personSex, personPhones, personBirthday, personDocumentCpf, personDocumentRg, personAddresses, personStatus);
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

    public Pacient(){
    }

    public void inactiveStatusPacient() {
        if (getPersonStatus()) {
            setPersonStatus(false);
        }
    }

    public void activeStatusPacient() {
        if (!getPersonStatus()) {
            setPersonStatus(true);
        }
    }

}
