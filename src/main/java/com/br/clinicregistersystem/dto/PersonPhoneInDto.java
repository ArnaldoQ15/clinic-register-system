package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.util.enums.PersonPhoneType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonPhoneInDto {

    private Long phoneId;
    private PersonPhoneType type;
    private Long number;
    private String personPhoneName;
    private Boolean phoneStatus;

}
