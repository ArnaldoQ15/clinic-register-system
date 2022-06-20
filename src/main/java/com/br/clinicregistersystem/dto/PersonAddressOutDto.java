package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.model.FederativeUnits;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonAddressOutDto {

    private String street;
    private String number;
    private String district;
    private String complement;
    private FederativeUnits state;
    private String city;
    private Integer postalCode;

}
