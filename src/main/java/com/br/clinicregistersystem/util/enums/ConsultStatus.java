package com.br.clinicregistersystem.util.enums;

import lombok.Getter;

@Getter
public enum ConsultStatus {

    PENDING_AUTHORIZATION("Pending for approvation"),
    PENDING_PAYMENT("Pending for payment"),
    APPROVED("Approved. Waiting consult"),
    FINISHED("Consult finished"),
    CANCELED_BY_PACIENT("Canceled by pacient"),
    CANCELED_BY_CLINIC("Canceled by clinic");

    private final String description;

    ConsultStatus(String description) {
        this.description = description;
    }

}
