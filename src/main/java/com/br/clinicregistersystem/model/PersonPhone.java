package com.br.clinicregistersystem.model;

import com.br.clinicregistersystem.util.enums.PersonPhoneType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class PersonPhone {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phoneId;

    @Column
    @Enumerated(EnumType.STRING)
    private PersonPhoneType type;

    @Column
    private Long number;

    @Column
    @Size(max = 80)
    private String personPhoneName;

    @ManyToOne
    @JoinColumn(name = "personId")
    @ToString.Exclude
    private Person person;

    @Column
    private OffsetDateTime registerDate;

    @Column
    private OffsetDateTime lastUpdate;

    @Column
    private Boolean phoneStatus;

}
