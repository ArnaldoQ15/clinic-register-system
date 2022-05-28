package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity(name = "consult_status")
public class ConsultStatusModel {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consult_status_id")
    private Long id;

    @NotNull
    @Column(name = "consult_status_description")
    private ConsultStatus description;

    @NotNull
    @Column(name = "consult_status_date_register")
    private OffsetDateTime registerDate;

}
