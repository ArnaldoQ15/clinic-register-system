package com.br.clinicregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;

@Getter
@Setter
@Table(name = "person_funcionary")
public class PersonFuncionary extends Person {

    @NotNull
    @Id
    @Column(name = "funcionary_id")
    private Long id;

    @NotNull
    @Column(name = "funcionary_role")
    private PersonFuncionaryRole role;

    @NotNull
    @Column(name = "funcionary_admission_date")
    private SimpleDateFormat admission_date;

    @NotNull
    @Email
    @Column(name = "funcionary_email")
    private String funcionary_email;

}