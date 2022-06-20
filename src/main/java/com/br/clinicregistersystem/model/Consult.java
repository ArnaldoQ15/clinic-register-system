package com.br.clinicregistersystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Consult {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consultId;

    @ManyToOne(cascade = CascadeType.ALL)
    private PersonPacient personPacient;

    @OneToOne(cascade = CascadeType.ALL)
    private PersonDoctor personDoctor;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column
    @Enumerated(EnumType.STRING)
    private ConsultStatus status;

    @Column
    @Enumerated(EnumType.STRING)
    private MedicalEspeciality medicalEspeciality;

    @Column
    @Enumerated(EnumType.STRING)
    private DayWeek dayRequest;

    @Column
    @Enumerated(EnumType.STRING)
    private DayHour hourRequest;

    @Column
    private OffsetDateTime registerDate;

    @Column
    private OffsetDateTime lastStatusUpdate;

}