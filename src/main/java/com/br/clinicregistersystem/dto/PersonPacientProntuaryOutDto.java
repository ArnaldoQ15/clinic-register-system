package com.br.clinicregistersystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonPacientProntuaryOutDto {

    private String pacientName;
    private Boolean firstTime;
    private String symptoms;
    private OffsetDateTime registerDate;
    private OffsetDateTime lastUpdate;

}
