package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.util.enums.FederativeUnits;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

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
    private OffsetDateTime registerDate;
    private OffsetDateTime lastUpdate;
    private Boolean addressStatus;

}
