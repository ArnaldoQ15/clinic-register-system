package com.br.clinicregistersystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonPacientChildInDto {

    private Long childId;
    private String childName;

}
