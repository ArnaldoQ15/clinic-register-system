package com.br.clinicregistersystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

@NoArgsConstructor
@Data
@Entity
public class PacientProntuary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long prontuaryId;

    @Column
    private Boolean firstTime = true;

    @Column
    private String symptoms;

    @Column
    private OffsetDateTime lastRegisterDate;

}
