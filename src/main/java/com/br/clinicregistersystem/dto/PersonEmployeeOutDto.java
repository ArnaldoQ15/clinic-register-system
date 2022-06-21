package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.util.enums.PersonEmployeeRole;
import com.br.clinicregistersystem.util.enums.PersonSex;
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
public class PersonEmployeeOutDto {

    private String personName;
    private String personEmail;
    private PersonSex personSex;
    private List<PersonPhoneOutDto> personPhones = new ArrayList<>();
    private LocalDate personBirthday;
    private List<PersonAddressOutDto> personAddresses = new ArrayList<>();
    private Boolean personStatus;
    private PersonEmployeeRole role;
    private LocalDate admissionDate;
    private LocalDate shutdownDate;
    private String institucionalEmail;

}
