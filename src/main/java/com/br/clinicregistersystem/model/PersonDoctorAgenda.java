package com.br.clinicregistersystem.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class PersonDoctorAgenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long agendaId;

    @ManyToOne
    @JoinColumn(name = "personId")
    @ToString.Exclude
    private PersonDoctor personDoctor;

    @Column
    @Enumerated(EnumType.STRING)
    private MedicalEspeciality medicalEspeciality;

    @Column
    @Enumerated(EnumType.STRING)
    private DayWeek dayWeek;

    @Column
    private Boolean dataStatus = false;

    @Column
    private Boolean morning0800 = false;

    @Column
    private Boolean morning0830 = false;

    @Column
    private Boolean morning0900 = false;

    @Column
    private Boolean morning0930 = false;

    @Column
    private Boolean morning1000 = false;

    @Column
    private Boolean morning1030 = false;

    @Column
    private Boolean morning1100 = false;

    @Column
    private Boolean morning1130 = false;

    @Column
    private Boolean afternoon1400 = false;

    @Column
    private Boolean afternoon1430 = false;

    @Column
    private Boolean afternoon1500 = false;

    @Column
    private Boolean afternoon1530 = false;

    @Column
    private Boolean afternoon1600 = false;

    @Column
    private Boolean afternoon1630 = false;

    @Column
    private Boolean afternoon1700 = false;

    @Column
    private Boolean afternoon1730 = false;

    @Column
    private Boolean night1800 = false;

    @Column
    private Boolean night1830 = false;

    @Column
    private Boolean night1900 = false;

    @Column
    private Boolean night1930 = false;

}
