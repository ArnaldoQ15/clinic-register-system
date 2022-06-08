package com.br.clinicregistersystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Email;

@Data
@PrimaryKeyJoinColumn(name = "personId")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PersonEmployee extends Person {

    @Column
    private Long funcionaryId;

    @Column
    private PersonEmployeeRole role;

    @Column
    private String admissionDate;

    @Column
    @Email
    private String institucionalEmail;

}