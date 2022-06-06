package com.br.clinicregistersystem.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class PersonDoctorHourSaturday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(unique=true, nullable=false)
    private Long doctorHourSaturdayId;

    @ManyToOne(cascade=CascadeType.ALL)
    private PersonDoctor personDoctor;

    @Column
    @NotNull
    private String doctorName;

    @Column
    @NotNull
    private MedicalEspeciality medicalEspeciality;

    @Column
    @NotNull
    private Boolean m0800;

    @Column
    @NotNull
    private Boolean m0830;

    @Column
    @NotNull
    private Boolean m0900;

    @Column
    @NotNull
    private Boolean m0930;

    @Column
    @NotNull
    private Boolean m1000;

    @Column
    @NotNull
    private Boolean m1030;

    @Column
    @NotNull
    private Boolean m1100;

    @Column
    @NotNull
    private Boolean m1130;


    public PersonDoctorHourSaturday() {
        this.m0800 = false;
        this.m0830 = false;
        this.m0900 = false;
        this.m0930 = false;
        this.m1000 = false;
        this.m1030 = false;
        this.m1100 = false;
        this.m1130 = false;
    }

}
