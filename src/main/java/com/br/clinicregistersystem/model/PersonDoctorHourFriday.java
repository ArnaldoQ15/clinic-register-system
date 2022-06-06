package com.br.clinicregistersystem.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class PersonDoctorHourFriday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(unique=true, nullable=false)
    private Long doctorHourFridayId;

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

    @Column
    @NotNull
    private Boolean a1400;

    @Column
    @NotNull
    private Boolean a1430;

    @Column
    @NotNull
    private Boolean a1500;

    @Column
    @NotNull
    private Boolean a1530;

    @Column
    @NotNull
    private Boolean a1600;

    @Column
    @NotNull
    private Boolean a1630;

    @Column
    @NotNull
    private Boolean a1700;

    @Column
    @NotNull
    private Boolean a1730;

    @Column
    @NotNull
    private Boolean n1800;

    @Column
    @NotNull
    private Boolean n1830;

    @Column
    @NotNull
    private Boolean n1900;

    @Column
    @NotNull
    private Boolean n1930;

    public PersonDoctorHourFriday() {
        this.m0800 = false;
        this.m0830 = false;
        this.m0900 = false;
        this.m0930 = false;
        this.m1000 = false;
        this.m1030 = false;
        this.m1100 = false;
        this.m1130 = false;
        this.a1400 = false;
        this.a1430 = false;
        this.a1500 = false;
        this.a1530 = false;
        this.a1600 = false;
        this.a1630 = false;
        this.a1700 = false;
        this.a1730 = false;
        this.n1800 = false;
        this.n1830 = false;
        this.n1900 = false;
        this.n1930 = false;
    }

}
