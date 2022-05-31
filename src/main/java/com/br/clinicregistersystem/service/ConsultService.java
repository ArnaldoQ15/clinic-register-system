package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.ConsultRepository;
import com.br.clinicregistersystem.model.*;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

@Service
public class ConsultService {

    public static void main(String[] args) {
        PersonPhone[] telefone = new PersonPhone[2];
        PersonAddress[] endereco = new PersonAddress[2];
        Doctor[] doutorLinguica = new Doctor[1];
        SimpleDateFormat validade = new SimpleDateFormat("09/07/2028");
        Pacient[] novoPaciente = new Pacient[1];
        Consult[] novaConsulta = new Consult[1];
        PacientChild[] crianca = new PacientChild[1];
        PacientHealthInsurance[] planoDeSaude = new PacientHealthInsurance[1];
        PersonPacientProntuary[] prontuario = new PersonPacientProntuary[1];


        telefone[0] = new PersonPhone(PersonPhoneType.CELLPHONE, 76695844558L, "Arnaldina da Silva Costa");

        telefone[1] = new PersonPhone(PersonPhoneType.LANDLINE, 21954698547L, "Papai Lucas");

//
//        ---------------------------------------------------------------------------
//


        endereco[0] = new PersonAddress("Rua das Gaivotas", "9", "Andorinhas", "Dimas",
                FederativeUnits.BA, "Salvador", 40000000);

        endereco[1] = new PersonAddress("Avenida Brasil", "20R", "Caldeiras", "", FederativeUnits.RJ,
                "Rio de Janeiro", 48425741);

//
//        ---------------------------------------------------------------------------
//

        doutorLinguica[0] = new Doctor("Girafales da Silva Linguiça", 49, "algumacoisaai@gmail.com", PersonSex.MALE,
                telefone[0], 1234567891L, 987654321L, endereco[0], 285128129,
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
                telefone[1], 45114535671L, 4561478541L, endereco[1], crianca[0], planoDeSaude[0],
                prontuario[0]);


//
//        ---------------------------------------------------------------------------
//

        novaConsulta[0] = new Consult(novoPaciente[0], "Cardiology", "Tuesday", "11:00 AM");


        ArrayList<String> doctorHours = new ArrayList<String>();
        ArrayList<String> doctorDays = new ArrayList<String>();
        ArrayList<Boolean> disponivelDoutor = new ArrayList<Boolean>();


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









//        if (positionOnIndex != null) {
//            Boolean b = testador = true;
//        }





//        if ((horarioDoDoutorTerca.contains(consultHourRequest) && (horarioDoDoutorTerca.indexOf(posicao) == true))) {
//            disponivelDoutorTerca.set(disponivelDoutorTerca.indexOf(posicao), false);
//        }





    }

}
