package com.br.clinicregistersystem.dto;

import com.br.clinicregistersystem.util.enums.PersonSex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonPacientChildOutDto {

    private String responsable;
    private Long responsablePersonId;
    private String childName;
    private Integer childAge;
    private PersonSex childSex;
    private OffsetDateTime registerDate;
    private OffsetDateTime lastUpdate;

}
