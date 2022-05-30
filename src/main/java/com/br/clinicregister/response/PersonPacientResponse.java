package com.br.clinicregister.response;

import com.br.clinicregister.model.PersonPacientChild;
import com.br.clinicregister.model.PersonPacientHealthInsurance;
import com.br.clinicregister.model.PersonPacientProntuary;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonPacientResponse {

    private Long id;
    private PersonPacientChild pacientChild;
    private PersonPacientHealthInsurance healthInsurance;
    private PersonPacientProntuary prontuary;

}
