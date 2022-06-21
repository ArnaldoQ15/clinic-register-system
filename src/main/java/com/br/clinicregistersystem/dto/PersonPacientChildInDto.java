package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.util.enums.PersonSex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonPacientChildInDto {

    private Long childId;
    private String childName;
    private LocalDate childBirthday;
    private PersonSex childSex;
    private Boolean printedTerm;

}
