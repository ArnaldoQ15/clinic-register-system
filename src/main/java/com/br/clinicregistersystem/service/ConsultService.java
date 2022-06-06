package com.br.clinicregistersystem.service;

import com.br.clinicregistersystem.domain.repository.ConsultRepository;
import com.br.clinicregistersystem.domain.repository.PersonDoctorRepository;
import com.br.clinicregistersystem.domain.repository.PersonPacientRepository;
import com.br.clinicregistersystem.dto.ConsultDto;
import com.br.clinicregistersystem.model.Consult;
import com.br.clinicregistersystem.model.ConsultStatus;
import com.br.clinicregistersystem.model.PersonDoctor;
import com.br.clinicregistersystem.model.PersonPacient;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConsultService {

    private ConsultRepository consultRepository;
    private PersonDoctorRepository personDoctorRepository;
    private DoctorHourService doctorHourService;

    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private PersonPacientRepository personPacientRepository;


    /**Find a consult by person ID.*/
    public List<ConsultDto> searchByPersonId(Long personId) {
        List<Consult> consults = consultRepository.findByPersonId(personId);
        return convertToDto(consults);
    }


    /**Save a consult on database.*/
    @Transactional
    public Consult saveConsult (Consult consult, Long personId) {
        consult.setRegisterDate(OffsetDateTime.now());
        consult.setStatus(ConsultStatus.PENDING);
        Optional<PersonPacient> person = personPacientRepository.findById(personId);
        consult.setPersonPacient(person.get());
        consult.setPersonId(person.get().getPersonId());
        PersonDoctor doutor = personDoctorRepository.findByMedicalEspeciality(consult.getConsultEspeciality());
        consult.setPersonDoctor(doutor);
        Consult map = modelMapper.map(consult, Consult.class);
        ResponseEntity<Consult> statusCode = doctorHourService.checkDoctorHours(map);
        return canMarkConsult(consult, statusCode);
    }


    /**Method of check the doctor agenda.*/
    @Nullable
    private Consult canMarkConsult(Consult consult, ResponseEntity<Consult> statusCode) {
        if (statusCode.getStatusCodeValue() == 200) {
            return consultRepository.save(consult);
        } else {
            ResponseEntity.badRequest().build();
            return null;
        }
    }


    /**Convert entity list to Dto list.*/
    public List<ConsultDto> convertToDto(List<Consult> consult) {
        return consult.stream().map(ConsultDto::new).collect(Collectors.toList());
    }

}