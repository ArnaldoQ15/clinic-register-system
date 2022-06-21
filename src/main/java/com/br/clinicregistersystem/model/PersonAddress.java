package com.br.clinicregistersystem.model;

import com.br.clinicregistersystem.util.enums.FederativeUnits;
import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class PersonAddress {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column
    private String street;

    @Column
    private String number;

    @Column
    private String district;

    @Column
    private String complement;

    @Column
    @Enumerated(EnumType.STRING)
    private FederativeUnits state;

    @Column
    private String city;

    @Column
    private Integer postalCode;

    @ManyToOne
    @JoinColumn(name = "personId")
    @ToString.Exclude
    private Person person;

    @Column
    private OffsetDateTime registerDate;

    @Column
    private OffsetDateTime lastUpdate;

    @Column
    private Boolean addressStatus;

}
