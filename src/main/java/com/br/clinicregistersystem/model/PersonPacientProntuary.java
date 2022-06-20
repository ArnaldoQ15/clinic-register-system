package com.br.clinicregistersystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
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

    @ManyToOne
    @JoinColumn(name = "personId")
    @ToString.Exclude
    private PersonPacient pacient;

}
