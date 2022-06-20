package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonRepository;
import com.br.clinicregistersystem.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;


    /**Validate if a person CPF exists on database.*/
    public void validateCpfExists(String cpf) {
        if (Boolean.TRUE.equals(repository.existsByPersonDocumentCpf(cpf)))
            throw new BadRequestException("A user with this CPF already exists.");
    }


    /**Validate if a person e-mail exists on database.*/
    public void validateEmailExists(String email) {
        if (Boolean.TRUE.equals(repository.existsByPersonEmail(email)))
            throw new BadRequestException("A user with this e-mail already exists.");
    }


    /**Set person's age on database.*/
    public Integer insertAge(LocalDate birthday) {
        Period periodAge = Period.between(birthday, LocalDate.now());
        return Math.abs(periodAge.getYears());
    }


    /**Update person's age method.*/
    public Integer makeBirthday(LocalDate birthday, Integer age) {
        if ((birthday.getMonthValue() == LocalDate.now().getMonthValue()) &&
                (birthday.getDayOfMonth() == LocalDate.now().getDayOfMonth()))
            return age + 1;
        throw new BadRequestException("Today is not your birthday :'(");
    }

}
