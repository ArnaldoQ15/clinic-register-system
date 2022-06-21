package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.util.enums.PersonPhoneType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonPhoneOutDto {

    private PersonPhoneType type;
    private Long number;
    private String personPhoneName;
    private OffsetDateTime registerDate;
    private OffsetDateTime lastUpdate;
    private Boolean phoneStatus;

}
