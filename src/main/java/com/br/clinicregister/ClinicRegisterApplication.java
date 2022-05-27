package com.br.clinicregister;

import com.br.clinicregister.model.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

@SpringBootApplication
public class ClinicRegisterApplication {

    public static void main(String[] args) {
//        SpringApplication.run(ClinicRegisterApplication.class, args);

        Locale Brasil = new Locale("pt", "BR");
        DateFormat dateFormatBr = DateFormat.getDateInstance(DateFormat.FULL, Brasil);

        Calendar validadeTeste = Calendar.getInstance();
        validadeTeste.set(2025, Calendar.FEBRUARY, 20);

        Person teste[] = new Person[2];
        PersonPhone inserir[] = new PersonPhone[2];
        PersonAddress endereco[] = new PersonAddress[2];
        PersonDoctorAgenda agendaDoDoutor[] = new PersonDoctorAgenda[2];

        agendaDoDoutor[0] = new PersonDoctorAgenda(PersonDoctorAgendaScales.MORNING);

        endereco[0] = new PersonAddress("Rua das Gaivotas", 9, "Andorinhas", "Dimas",
                FederativeUnits.BA, "Salvador", 40000000);

        inserir[0] = new PersonPhone(PersonPhoneType.CELLPHONE, 766958445, "Trolling");

//        teste[0] = new Person("Testador", 29, "algumacoisaai@gmail.com", PersonSex.MALE,
//                inserir[0], 1234567891, 987654321, endereco[0]);

        PersonDoctor testeDoutor[] = new PersonDoctor[2];

        testeDoutor[0] = new PersonDoctor("Testador", 29, "algumacoisaai@gmail.com", PersonSex.MALE,
                inserir[0], 1234567891, 987654321, endereco[0], 285128129,
                FederativeUnits.BA, validadeTeste, PersonDoctorEspeciality.MEDICAL_CLINIC, agendaDoDoutor[0]);

    }

}
