package com.br.clinicregistersystem.util.enums;

import lombok.Getter;

@Getter
public enum FederativeUnits {

    AC("Acre"),
    AL("Alagoas"),
    AM("Amazonas"),
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
    RN("Rio Grande do Norte"),
    RO("Rondonia"),
    RR("Roraima"),
    RS("Rio Grande do Sul"),
    SC("Santa Catarina"),
    SE("Sergipe"),
    SP("Sao Paulo"),
    TO("Tocantins");

    private final String description;

    FederativeUnits(String description) {
        this.description = description;
    }

}
