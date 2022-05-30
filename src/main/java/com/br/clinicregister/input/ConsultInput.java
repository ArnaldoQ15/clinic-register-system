package com.br.clinicregister.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Getter
@Setter
public class ConsultInput {

    @Valid
    @NotNull
    private PersonIdInput personId;

    @Valid
    @NotNull
    private PersonDoctorInput doctor;

    @Valid
    @NotNull
    private Calendar date;

    @Valid
    @NotNull
    private String hour;

}
