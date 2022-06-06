package com.br.clinicregistersystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Data
@Entity(name = "personPhone")
public class PersonPhone {

    @Id
    @Column(name = "phone_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phoneId;

    @Column(name = "phone_type")
    private PersonPhoneType type;

    @Column(name = "phone_number")
    private Long number;

    @Column(name = "phone_name")
    @Size(max = 80)
    protected String personPhoneName;

    public PersonPhone(PersonPhoneType type, Long number, String personPhoneName) {
        this.type = type;
        this.number = number;
        this.personPhoneName = personPhoneName;
    }

}
