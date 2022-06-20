package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.model.PersonSex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
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
    private PersonPacientChildInDto pacientChildren;
    private PersonPacientHealthInsuranceInDto healthInsurance;
    private PersonPacientProntuaryInDto prontuary;

}
