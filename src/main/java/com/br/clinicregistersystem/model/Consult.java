package com.br.clinicregistersystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@NoArgsConstructor
@Data
@Entity
public class Consult {


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consultId;

    @ManyToOne(cascade = CascadeType.ALL)
    private Pacient pacient;

    private Long personId;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Doctor doctor;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column
    private ConsultStatus status;

    @Column(name = "consult_especiality")
    private DoctorEspeciality consultEspeciality;

    @Column(name = "consult_date")
    private String consultDateRequest;

    @Column(name = "consult_hour")
    private String consultHourRequest;

    @Column(name = "consult_register_date")
    private OffsetDateTime registerDate;

//    public void setPersonId(Long personId) {
//        this.personId = pacient.getPersonId();
//    }

    //    public Consult(Pacient pacient, DoctorEspeciality consultEspeciality, String consultDateRequest, String consultHourRequest) {
//        this.pacient = pacient;
//        this.consultEspeciality = consultEspeciality;
//        this.consultDateRequest = consultDateRequest;
//        this.consultHourRequest = consultHourRequest;
//    }

}