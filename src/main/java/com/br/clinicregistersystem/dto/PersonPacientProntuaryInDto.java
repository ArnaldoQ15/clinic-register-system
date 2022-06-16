package com.br.clinicregistersystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonPacientProntuaryInDto {

    private Long prontuaryId;
    private Boolean firstTime;
    private String symptoms;

}
