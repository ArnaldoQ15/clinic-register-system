package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonRepository;
import com.br.clinicregistersystem.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
@AllArgsConstructor
public class PersonService {

    @Autowired
    private PersonRepository repository;


    /**Validate if a person CPF exists on database.*/
    public void validateCpfExists(String cpf) {
        if (Boolean.TRUE.equals(repository.existsByPersonDocumentCpf(cpf)))
            throw new BusinessException("There is already a person registered with this CPF.");
    }


    /**Validate if a person e-mail exists on database.*/
    public void validateEmailExists(String email) {
        if (Boolean.TRUE.equals(repository.existsByPersonEmail(email)))
            throw new BusinessException("There is already a person registered with this e-mail.");
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
        throw new BusinessException("Today isn't your birthday :'(");
    }

}
