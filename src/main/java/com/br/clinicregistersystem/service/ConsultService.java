package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.ConsultRepository;
import com.br.clinicregistersystem.model.*;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class ConsultService {

    public static void main(String[] args) throws ParseException {
        PersonPhone[] telefone = new PersonPhone[3];
        PersonAddress[] endereco = new PersonAddress[2];
        List<PersonAddress> novoEndereco = new ArrayList <PersonAddress>();
        Doctor[] doutorLinguica = new Doctor[1];
        Pacient[] novoPaciente = new Pacient[1];
        Consult[] novaConsulta = new Consult[1];
        PacientChild[] crianca = new PacientChild[1];
        PacientHealthInsurance[] planoDeSaude = new PacientHealthInsurance[1];
        PersonPacientProntuary[] prontuario = new PersonPacientProntuary[1];



        telefone[0] = new PersonPhone(PersonPhoneType.CELLPHONE, 76695844558L, "Arnaldina da Silva Costa");

        telefone[1] = new PersonPhone(PersonPhoneType.LANDLINE, 21954698547L, "Papai Lucas");

        telefone[2] = new PersonPhone(PersonPhoneType.CELLPHONE, 719451458789L, "");


        List<PersonPhone> myPhones = new ArrayList<>();
        myPhones.add(telefone[0]);
        myPhones.add(telefone[2]);

        List<PersonPhone> myPhones2 = new ArrayList<>();
        myPhones2.add(telefone[1]);

//
//        ---------------------------------------------------------------------------
//

        String realDate = "03/08/1994";
        DateTimeFormatter transfer = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate myBirth = LocalDate.parse(realDate, transfer);

        String realDate2 = "01/06/2000";
        DateTimeFormatter transfer2 = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate myBirth2 = LocalDate.parse(realDate2, transfer2);

        String realDate3 = "09/07/2028";
        DateTimeFormatter transfer3 = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate validade = LocalDate.parse(realDate3, transfer3);


//
//        ---------------------------------------------------------------------------
//


        endereco[0] = new PersonAddress("Rua das Gaivotas", "9", "Andorinhas", "Dimas",
                FederativeUnits.BA, "Salvador", 40000000);

        endereco[1] = new PersonAddress("Avenida Brasil", "20R", "Caldeiras", "", FederativeUnits.RJ,
                "Rio de Janeiro", 48425741);


        List<PersonAddress> myLocale = new ArrayList<>();
        myLocale.add(endereco[0]);

        List<PersonAddress> myLocale2 = new ArrayList<>();
        myLocale2.add(endereco[1]);

//
//        ---------------------------------------------------------------------------
//

        doutorLinguica[0] = new Doctor("Girafales da Silva Linguiça", 49, "algumacoisaai@gmail.com", PersonSex.MALE,
                myPhones, myBirth, 1234567891L, 987654321L, myLocale, true, 285128129,
                FederativeUnits.BA, validade, DoctorEspeciality.MEDICAL_CLINIC);

//
//        ---------------------------------------------------------------------------
//


        crianca[0] = new PacientChild("Adalbertinho Oliveirinha", 8, PersonSex.MALE, true);


//
//        ---------------------------------------------------------------------------
//


        planoDeSaude[0] = new PacientHealthInsurance(PacientHealthInsuranceName.HAPVIDA_ASSISTENCIA_MEDICA, 85482465L,
                PacientHealthInsuranceCoverage.ADVANCED);


//
//        ---------------------------------------------------------------------------
//


        prontuario[0] = new PersonPacientProntuary(false, "Muita dor de cabeça e dor nas costas.");


//
//        ---------------------------------------------------------------------------
//

        novoPaciente[0] = new Pacient("Dilma Roussef", 59, "dilma@dobrasil.com.br", PersonSex.FEMALE,
                myPhones2, myBirth2, 45114535671L, 4561478541L, myLocale2, true, crianca[0], planoDeSaude[0],
                prontuario[0]);


//
//        ---------------------------------------------------------------------------
//

        novaConsulta[0] = new Consult(novoPaciente[0], "Cardiology", "Tuesday", "11:00 AM");


        List<String> doctorHours = new ArrayList<String>();
        List<String> doctorDays = new ArrayList<String>();
        List<Boolean> disponivelDoutor = new ArrayList<Boolean>();


        ConsultRepository consultRepository;
        Consult consult;


        for (DoctorScalesEspeciality runnerForVar : DoctorScalesEspeciality.values()) {
            if ((Objects.equals(runnerForVar.getEspeciality(), novaConsulta[0].getConsultEspeciality())) &&
                    (Objects.equals(runnerForVar.getDay(), novaConsulta[0].getConsultDateRequest()))) {
                doctorHours.add(runnerForVar.getHour());
                doctorDays.add(runnerForVar.getDay());
            }
        }


        for (DoctorScalesEspeciality nomes : DoctorScalesEspeciality.values()) {
            if ((nomes.getEspeciality() == novaConsulta[0].getConsultEspeciality()) &&
                    (nomes.getDay() == novaConsulta[0].getConsultDateRequest())) {
                if (disponivelDoutor.equals(false)) {
                } else {
                    disponivelDoutor.add(true);
                }
            }
        }

        disponivelDoutor.set(1, false);
        disponivelDoutor.set(6, false);


        Integer positionOnIndex = doctorHours.indexOf(novaConsulta[0].getConsultHourRequest());



            Iterator removeFalso = doctorHours.iterator();
            disponivelDoutor.iterator().hasNext();
            if ((doctorHours.get(positionOnIndex).contains(novaConsulta[0].getConsultHourRequest())) &&
                    (doctorDays.get(positionOnIndex).contains(novaConsulta[0].getConsultDateRequest())) &&
                    (disponivelDoutor.get(positionOnIndex).equals(false))) {
//                Parâmetro para passar caso contenha a hora e a data da consulta dr. na lista mas está indisponível (falso)
            } else
                System.out.println("Nao ta falso");





        Iterator percorredor = disponivelDoutor.iterator();
        if (doctorDays.contains(novaConsulta[0].getConsultDateRequest())) {
            disponivelDoutor.iterator().hasNext();
            if (doctorHours.contains(novaConsulta[0].getConsultHourRequest())) {
                disponivelDoutor.iterator().hasNext();
                if (disponivelDoutor.get(positionOnIndex).equals(true)) {
                    disponivelDoutor.set(positionOnIndex, false);
                    System.out.println("Teste de sucesso");
                } else
                    System.out.println("Teste de falha");
            } else
                System.out.println("Não há registro no sistema desta especialidade para este horário.");
        } else
            System.out.println("O médico não atende neste dia");


        disponivelDoutor.set(positionOnIndex, false);



        novoPaciente[0].getPersonBirthday();




//        if (positionOnIndex != null) {
//            Boolean b = testador = true;
//        }





//        if ((horarioDoDoutorTerca.contains(consultHourRequest) && (horarioDoDoutorTerca.indexOf(posicao) == true))) {
//            disponivelDoutorTerca.set(disponivelDoutorTerca.indexOf(posicao), false);
//        }


        novoPaciente[0].makeBirthday(novoPaciente[0].getPersonBirthday());



    }

}
