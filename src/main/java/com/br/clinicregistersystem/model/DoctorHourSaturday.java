package com.br.clinicregistersystem.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity(name = "doctorHourSaturday")
public class DoctorHourSaturday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "doctorHourSaturdayId",unique=true, nullable=false)
    private Long doctorHourSaturdayId;

    @ManyToOne(cascade=CascadeType.ALL)
    private Doctor doctor;

    @Column(name = "doctorName")
    @NotNull
    private String doctorName;

    @Column(name = "doctorEspeciality")
    @NotNull
    private String doctorEspeciality;

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


    public DoctorHourSaturday() {
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
