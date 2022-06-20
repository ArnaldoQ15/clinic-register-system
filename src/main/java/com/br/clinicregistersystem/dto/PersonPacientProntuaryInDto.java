package com.br.clinicregistersystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonPacientProntuaryInDto {

    private Long prontuaryId;
    private Boolean firstTime;
    private String symptoms;

}
