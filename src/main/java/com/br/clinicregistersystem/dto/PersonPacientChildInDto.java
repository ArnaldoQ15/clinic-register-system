package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.model.PersonSex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonPacientChildInDto {

    private Long childId;
    private String childName;
    private LocalDate childBirthday;
    private PersonSex childSex;
    private Boolean printedTerm;

}
