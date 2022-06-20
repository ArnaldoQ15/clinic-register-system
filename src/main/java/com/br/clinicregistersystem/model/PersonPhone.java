package com.br.clinicregistersystem.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

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

}
