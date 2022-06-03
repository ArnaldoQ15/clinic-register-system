package com.br.clinicregistersystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;

@NoArgsConstructor
@Data
@Entity(name = "pacientProntuary")
public class PersonPacientProntuary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prontuary_id")
    private Long prontuaryId;

    @Column(name = "prontuary_first_time")
    private Boolean firstTime;

    @NotBlank
    @Column(name = "prontuary_symptoms")
    private String symptoms;

    @Column(name = "prontuary_last_register_date")
    private OffsetDateTime lastRegisterDate;

    public PersonPacientProntuary(Boolean firstTime, String symptoms) {
        this.firstTime = true;
        this.symptoms = symptoms;
    }

}
