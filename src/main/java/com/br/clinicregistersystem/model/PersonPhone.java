package com.br.clinicregistersystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class PersonPhone {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phoneId;

    @Column
    private PersonPhoneType type;

    @Column
    private Long number;

    @Column
    @Size(max = 80)
    private String personPhoneName;

}
