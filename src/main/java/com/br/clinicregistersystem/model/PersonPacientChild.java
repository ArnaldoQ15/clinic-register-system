package com.br.clinicregistersystem.model;

import com.br.clinicregistersystem.util.enums.PersonSex;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class PersonPacientChild {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long childId;

    @Column
    private String responsable;

    @Column
    private String childName;

    @Column
    private Integer childAge;

    @Column
    private LocalDate childBirthday;

    @Column
    @Enumerated(EnumType.STRING)
    private PersonSex childSex;

    @Column
    private Boolean printedTerm;

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
