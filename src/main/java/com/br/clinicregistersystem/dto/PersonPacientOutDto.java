package com.br.clinicregistersystem.dto;

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
public class PersonPacientOutDto {

    private String personName;
    private String personEmail;
    private PersonSex personSex;
    private List<PersonPhoneOutDto> personPhones = new ArrayList<>();
    private LocalDate personBirthday;
    private List<PersonAddressOutDto> personAddresses = new ArrayList<>();
    private Boolean personStatus;
    private List<PersonPacientChildOutDto> pacientChildren;
    private PersonPacientHealthInsuranceOutDto healthInsurance;
    private List<PersonPacientProntuaryOutDto> prontuaries;

}
