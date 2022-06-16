package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.model.PersonPacientChild;
import com.br.clinicregistersystem.model.PersonPacientHealthInsurance;
import com.br.clinicregistersystem.model.PersonPacientProntuary;
import com.br.clinicregistersystem.model.PersonSex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonPacientInDto {

    public Long personId;
    private String personName;
    private String personEmail;
    private PersonSex personSex;
    private List<PersonPhoneInDto> personPhones = new ArrayList<>();
    private LocalDate personBirthday;
    private String personDocumentCpf;
    private String personDocumentRg;
    private List<PersonAddressInDto> personAddresses = new ArrayList<>();
    private Boolean personStatus;
    private PersonPacientChild pacientChild;
    private PersonPacientHealthInsurance healthInsurance;
    private PersonPacientProntuary prontuary;

}
