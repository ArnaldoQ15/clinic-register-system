package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.model.PersonSex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonPacientChildOutDto {

    private String responsable;
    private String childName;
    private Integer childAge;
    private PersonSex childSex;
    private OffsetDateTime registerDate;
    private OffsetDateTime lastUpdate;

}
