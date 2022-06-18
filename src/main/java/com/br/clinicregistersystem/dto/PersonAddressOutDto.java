package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.model.FederativeUnits;
import com.br.clinicregistersystem.model.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PersonAddressOutDto {

    private String street;
    private String number;
    private String district;
    private String complement;
    private FederativeUnits state;
    private String city;
    private Integer postalCode;

}
