package com.br.clinicregistersystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@PrimaryKeyJoinColumn(name = "personId")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class PersonDoctor extends Person {

    @Column
    private Integer professionalRegisterNumber;

    @Column
    @Enumerated(EnumType.STRING)
    private FederativeUnits professionalRegisterState;

    @Column
    private LocalDate professionalRegisterValidity;

    @Column
    @Enumerated(EnumType.STRING)
    @NotNull
    private MedicalEspeciality medicalEspeciality;

    @OneToMany(mappedBy = "agendaId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PersonDoctorAgenda> agenda = new ArrayList<>();

}
