package com.br.clinicregister.controller;

import com.br.clinicregister.domain.repository.ConsultsRepository;
import com.br.clinicregister.input.ConsultInput;
import com.br.clinicregister.mapper.ConsultsMapper;
import com.br.clinicregister.model.Consults;
import com.br.clinicregister.response.ConsultResponse;
import com.br.clinicregister.service.ConsultRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/{personId}/consults")
public class ConsultController {

    private ConsultsRepository consultsRepository;
    private ConsultRequestService consultRequestService;
    private ConsultsMapper consultsMapper;

//    Nova consulta no sistema da clínica
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ConsultResponse newConsult (@Valid @RequestBody ConsultInput consultInput) {
        Consults newConsult = consultsMapper.toEntity(consultInput);
        Consults consultRequested = consultRequestService.request(newConsult);

        return consultsMapper.toModel(consultRequested);
    }

}
