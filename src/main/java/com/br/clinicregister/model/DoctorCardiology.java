package com.br.clinicregister.model;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;

@Getter
@Entity(name = "doctor_especiality_cardiology")
public class DoctorCardiology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_appointment_id")
    private Long card_id;

    @OneToOne
    private PersonDoctor personDoctor;

    @Column(name = "dispo_{dispoDocTerca}")
    private ArrayList<Boolean> dispoDocTerca;

}
