package com.br.clinicregister.response;

import com.br.clinicregister.model.PersonDoctor;
import com.br.clinicregister.model.PersonDoctorEspeciality;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetTime;
import java.util.Calendar;

@Getter
@Setter
public class ConsultResponse {

    private Long consultId;
    private PersonDoctorEspeciality especiality;
    private PersonDoctor doctor;
    private Calendar dateConsult;
    private OffsetTime hourConsult;

}
