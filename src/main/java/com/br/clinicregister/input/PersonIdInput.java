package com.br.clinicregister.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PersonIdInput {

    @NotNull
    private Long id;

}
