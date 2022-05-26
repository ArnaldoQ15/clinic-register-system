package com.br.clinicregister.model;

import lombok.Getter;

@Getter
public enum PersonDoctorProfessionalRegisterState {

    AM("Amazonas"),
    AL("Alagoas"),
    AC("Acre"),
    AP("Amapa"),
    BA("Bahia"),
    CE("Ceara"),
    DF("Federal District"),
    ES("Espirito Santo"),
    GO("Goias"),
    MA("Maranhao"),
    MG("Minas Gerais"),
    MS("Mato Grosso do Sul"),
    MT("Mato Grosso"),
    PA("Para"),
    PB("Paraiba"),
    PE("Pernambuco"),
    PI("Piaui"),
    PR("Parana"),
    RJ("Rio de Janeiro"),
    RO("Rondonia"),
    RN("Rio Grande do Norte"),
    RR("Roraima"),
    RS("Rio Grande do Sul"),
    SC("Santa Catarina"),
    SE("Sergipe"),
    SP("Sao Paulo"),
    TO("Tocantins");

        private final String description;

    PersonDoctorProfessionalRegisterState(String description) {
        this.description = description;
    }

}
