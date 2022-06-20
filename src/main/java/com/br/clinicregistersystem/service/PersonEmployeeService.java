package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.PersonEmployeeRepository;
import com.br.clinicregistersystem.dto.PersonEmployeeInDto;
import com.br.clinicregistersystem.dto.PersonEmployeeOutDto;
import com.br.clinicregistersystem.exception.BadRequestException;
import com.br.clinicregistersystem.exception.NotFoundException;
import com.br.clinicregistersystem.model.Person;
import com.br.clinicregistersystem.model.PersonEmployee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PersonEmployeeService {

    @Autowired
    private PersonEmployeeRepository repository;

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonAddressService personAddressService;

    @Autowired
    private PersonPhoneService personPhoneService;

    @Autowired
    private ModelMapper modelMapper;


    /**Find all employees on Database.*/
    public List<PersonEmployeeOutDto> findAll() {
        List<PersonEmployee> employeeList = repository.findAll();
        List<PersonEmployeeOutDto> finalEmployeesOutDto = new ArrayList<>();

        employeeList.forEach(personEmployee -> {
            PersonEmployeeOutDto employeeOutDto = modelMapper.map(personEmployee, PersonEmployeeOutDto.class);
            finalEmployeesOutDto.add(employeeOutDto);
        });

        return finalEmployeesOutDto;
    }


    /**Find an employee by Person ID.*/
    public PersonEmployeeOutDto findId(Long personId) {
        Optional<PersonEmployee> employee = repository.findById(personId);
        if (employee.isEmpty())
            throw new NotFoundException("Employee not found.");

        return modelMapper.map(employee.get(), PersonEmployeeOutDto.class);
    }


    /**Save a employee on database.*/
    public ResponseEntity<PersonEmployee> persist(PersonEmployeeInDto dto) {
        personService.validateCpfExists(dto.getPersonDocumentCpf());
        personService.validateEmailExists(dto.getPersonEmail());

        PersonEmployee entityNew = new PersonEmployee();
        BeanUtils.copyProperties(dto, entityNew);

        entityNew.setPersonAge(personService.insertAge(entityNew.getPersonBirthday()));
        entityNew.setPersonAddresses(personAddressService.convertListToEntity(dto.getPersonAddresses(),
                modelMapper.map(entityNew, Person.class)));
        entityNew.setPersonPhones(personPhoneService.convertListToEntity(dto.getPersonPhones(),
                modelMapper.map(entityNew, Person.class)));

        entityNew.setPersonRegisterDate(OffsetDateTime.now());
        entityNew.setPersonStatus(true);
        entityNew.setAdmissionDate(LocalDate.now());

        repository.save(entityNew);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    /**Update employee method.*/
    public ResponseEntity<PersonEmployee> update(Long personId, PersonEmployeeInDto dto) {
        Optional<PersonEmployee> employee = repository.findById(personId);
        if (employee.isEmpty())
            throw new NotFoundException("Employee not found.");

        PersonEmployee entityNew = new PersonEmployee();
        BeanUtils.copyProperties(employee.get(), entityNew);

        if (!dto.getPersonEmail().equals(entityNew.getPersonEmail())) {
            personService.validateEmailExists(dto.getPersonEmail());
            entityNew.setPersonEmail(dto.getPersonEmail());
        }

        if (Objects.nonNull(dto.getInstitucionalEmail()) && !employee.get().getInstitucionalEmail().equals(dto.getInstitucionalEmail())
                && Boolean.TRUE.equals(repository.existsByInstitucionalEmail(dto.getInstitucionalEmail())))
            throw new BadRequestException("There is already an institutional email registered with the given address.");

        entityNew.setInstitucionalEmail(dto.getInstitucionalEmail());
        entityNew.setPersonName(dto.getPersonName());
        entityNew.setPersonStatus(dto.getPersonStatus());
        entityNew.setPersonSex(dto.getPersonSex());
        entityNew.setPersonBirthday(dto.getPersonBirthday());
        entityNew.setPersonLastUpdate(OffsetDateTime.now());

        repository.save(entityNew);
        return ResponseEntity.ok().build();
    }


    /**Inactive/active an employee by Person ID.*/
    public ResponseEntity<Void> delete(Long personId) {
        Optional<PersonEmployee> employee = repository.findById(personId);
        if (employee.isPresent()) {
            employee.get().setPersonStatus(false);
            employee.get().setPersonLastUpdate(OffsetDateTime.now());
            repository.save(employee.get());
            return ResponseEntity.noContent().build();
        } else {
            throw new NotFoundException("Employee not found.");
        }
    }

}
