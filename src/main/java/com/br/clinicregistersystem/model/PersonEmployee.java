package com.br.clinicregistersystem.model;

import com.br.clinicregistersystem.util.enums.PersonEmployeeRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@PrimaryKeyJoinColumn(name = "personId")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class PersonEmployee extends Person {

    @Column
    private String employeeName;

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