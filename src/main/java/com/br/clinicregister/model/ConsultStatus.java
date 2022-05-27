package com.br.clinicregister.model;

import lombok.Getter;

@Getter
public enum ConsultStatus {

    PENDING("Pending for approvation"),
    WAITING_TAX("Waiting taxes payment"),
    APPROVED("Approved. Waiting consult"),
    FINISHED("Consult finished");

    private final String description;

    ConsultStatus(String description) {
        this.description = description;
    }
}
