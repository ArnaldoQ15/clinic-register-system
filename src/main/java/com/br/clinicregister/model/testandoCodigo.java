package com.br.clinicregister.model;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public enum testandoCodigo {

    TESTE_1 ("testador 1", "cardio"),
    TESTE_2 ("testador 2", "cardio"),
    TESTE_3 ("testador 3", "angio");

    public final String description;
    public final String especiality;

    testandoCodigo(String description, String especiality) {
        this.description = description;
        this.especiality = especiality;
    }

    public static void main(String[] args) {
        ArrayList<String> testandoLogica = new ArrayList<String>();

        for (testandoCodigo nomes : testandoCodigo.values()) {
            if (nomes.getEspeciality() == "cardio")
                testandoLogica.add(nomes.getDescription());
        }

    }

}
