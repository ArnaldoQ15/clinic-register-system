package com.br.clinicregister;

import com.br.clinicregister.common.DateHelper;
import com.br.clinicregister.model.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;

@SpringBootApplication
public class ClinicRegisterApplication {

    public static void main(String[] args) {
//        SpringApplication.run(ClinicRegisterApplication.class, args);

        DateHelper dateHelper;

        SimpleDateFormat validade = new SimpleDateFormat("01/01/2028");


        PersonPhone[] telefone = new PersonPhone[1];
        PersonAddress[] endereco = new PersonAddress[1];
        PersonDoctorAgenda[] agenda = new PersonDoctorAgenda[1];

//        agenda[0] = new PersonDoctorAgenda(PersonDoctorAgendaScales.MORNING_AFTERNOON_NIGHT,
//                PersonDoctorAgendaWeekChoosedDays.MON_WED_SAT);

        endereco[0] = new PersonAddress("Rua das Gaivotas", "9", "Andorinhas", "Dimas",
                FederativeUnits.BA, "Salvador", 40000000);

        telefone[0] = new PersonPhone(PersonPhoneType.CELLPHONE, 766958445, "Arnaldina da Silva Costa");


        PersonDoctor[] doutorLinguica = new PersonDoctor[1];

        doutorLinguica[0] = new PersonDoctor("Girafales da Silva Linguiça", 49, "algumacoisaai@gmail.com", PersonSex.MALE,
                telefone[0], 1234567891, 987654321, endereco[0], 285128129,
                FederativeUnits.BA, validade, PersonDoctorEspeciality.MEDICAL_CLINIC, agenda[0]);
    }

}
