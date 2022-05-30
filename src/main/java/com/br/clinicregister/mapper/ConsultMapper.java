package com.br.clinicregister.mapper;

import com.br.clinicregister.input.ConsultInput;
import com.br.clinicregister.model.Consult;
import com.br.clinicregister.response.ConsultResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ConsultMapper {

    private ModelMapper modelMapper;

    public ConsultResponse toModel(Consult consult) {
        return modelMapper.map(consult, ConsultResponse.class);
    }

    public Consult toEntity(ConsultInput consultInput) {
        return modelMapper.map(consultInput, Consult.class);
    }

}
