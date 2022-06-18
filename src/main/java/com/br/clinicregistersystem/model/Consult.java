package com.br.clinicregistersystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private PersonPacient personPacient;

    private Long personId;

    @OneToOne(cascade = CascadeType.PERSIST)
    private PersonDoctor personDoctor;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column
    @Enumerated(EnumType.STRING)
    private ConsultStatus status;

    @Column
    @Enumerated(EnumType.STRING)
    private MedicalEspeciality consultEspeciality;

    @Column
    private String consultDateRequest;

    @Column
    private String consultHourRequest;

    @Column
    private OffsetDateTime registerDate;

}