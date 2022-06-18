package com.br.clinicregistersystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@PrimaryKeyJoinColumn(name = "personId")
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonEmployee extends Person {

    @Column
    @Enumerated(EnumType.STRING)
    private PersonEmployeeRole role;

    @Column
    private LocalDate admissionDate;

    @Column
    private LocalDate shutdownDate;

    @Column
    @Email
    private String institucionalEmail;

}