package com.br.clinicregister.input;

import com.br.clinicregister.model.PersonDoctorEspeciality;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PersonDoctorInput {

    @NotBlank
    private PersonDoctorEspeciality especiality;

    @NotBlank
    private String nameDoctor;

}
