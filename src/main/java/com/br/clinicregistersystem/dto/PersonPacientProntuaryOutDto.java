package com.br.clinicregistersystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonPacientProntuaryOutDto {

    private String personName;
    private Boolean firstTime;
    private String symptoms;
    private OffsetDateTime registerDate;
    private OffsetDateTime lastUpdate;

}
