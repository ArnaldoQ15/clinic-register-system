package com.br.clinicregister.controller;

import com.br.clinicregister.domain.repository.ConsultRepository;
import com.br.clinicregister.input.ConsultInput;
import com.br.clinicregister.mapper.ConsultMapper;
import com.br.clinicregister.model.Consult;
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

    private ConsultRepository consultRepository;
    private ConsultRequestService consultRequestService;
    private ConsultMapper consultMapper;

//    Nova consulta no sistema da clínica
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ConsultResponse newConsult (@Valid @RequestBody ConsultInput consultInput) {
        Consult newConsult = consultMapper.toEntity(consultInput);
        Consult consultRequested = consultRequestService.request(newConsult);

        return consultMapper.toModel(consultRequested);
    }

}
