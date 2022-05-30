package com.br.clinicregister.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.Calendar;

@Getter
@Setter
@Entity(name = "consult")
public class Consult {

    @NotNull
    @Id
    @Column(name = "consult_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consultId;

    @ManyToOne
    private PersonPacient personPacient;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToOne
    private ConsultStatus status;

    @NotNull
    @Column(name = "consult_especiality")
    private PersonDoctorEspeciality especiality;

    @NotNull
    @Column(name = "consult_doctor")
    private PersonDoctor doctor;

    @NotNull
    @Column(name = "consult_date")
    private Calendar dateConsult;

    @NotNull
    @Column(name = "consult_hour")
    private OffsetTime hourConsult;

    @NotNull
    @Column(name = "consult_register_date")
    protected OffsetDateTime registerDate;

    public Consult(PersonDoctorEspeciality especiality, PersonDoctor doctor, Calendar dateConsult,
                    OffsetTime hourConsult) {
        this.especiality = especiality;
        this.doctor = doctor;
        this.dateConsult = dateConsult;
        this.hourConsult = hourConsult;
    }

}
