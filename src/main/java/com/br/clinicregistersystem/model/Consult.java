package com.br.clinicregistersystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@NoArgsConstructor
@Data
@Entity(name = "consult")
public class Consult {


    @NotNull
    @Id
    @Column(name = "consult_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consultId;

    @ManyToOne(cascade = CascadeType.ALL)
    private Pacient pacient;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Doctor doctor;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ConsultStatus status;

    @NotNull
    @Column(name = "consult_especiality")
    private String consultEspeciality;

    @NotNull
    @Column(name = "consult_date")
    private String consultDateRequest;

    @NotNull
    @Column(name = "consult_hour")
    private String consultHourRequest;

    @NotNull
    @Column(name = "consult_register_date")
    private OffsetDateTime registerDate;


    public Consult(Pacient pacient, String consultEspeciality, String consultDateRequest, String consultHourRequest) {
        this.pacient = pacient;
        this.consultEspeciality = consultEspeciality;
        this.consultDateRequest = consultDateRequest;
        this.consultHourRequest = consultHourRequest;
    }

}