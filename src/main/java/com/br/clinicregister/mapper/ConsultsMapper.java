package com.br.clinicregister.mapper;

import com.br.clinicregister.input.ConsultInput;
import com.br.clinicregister.model.Consults;
import com.br.clinicregister.response.ConsultResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ConsultsMapper {

    private ModelMapper modelMapper;

    public ConsultResponse toModel(Consults consults) {
        return modelMapper.map(consults, ConsultResponse.class);
    }

    public Consults toEntity(ConsultInput consultInput) {
        return modelMapper.map(consultInput, Consults.class);
    }

}
