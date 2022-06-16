package com.br.clinicregistersystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class PersonPacientProntuary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long prontuaryId;

    @Column
    private Boolean firstTime;

    @Column
    private String symptoms;

    @Column
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime registerDate;

    @Column
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime lastUpdate;

    private Long personId;

    @ManyToOne
    @JoinColumn(name = "pacientId")
    private PersonPacient pacient;

}
