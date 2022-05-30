package com.br.clinicregister.service;

import com.br.clinicregister.domain.repository.DoctorHoursRepository;
import com.br.clinicregister.domain.repository.DoctorRepository;
import com.br.clinicregister.model.PersonPacient;
import org.springframework.stereotype.Service;

@Service
public class DoctorAgendaService {

    DoctorRepository doctorRepository;
    DoctorHoursRepository doctorHoursRepository;

    PersonPacient personPacient;


///////// Para retornar o dia da semana:
//    public static String getWeek(String date){ // Format: 31/12/2022
//        String dayWeek = "Day of week";
//        GregorianCalendar gc = new GregorianCalendar();
//        try {
//            gc.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(date));
//            switch (gc.get(Calendar.DAY_OF_WEEK)) {
//                case Calendar.SUNDAY -> dayWeek = "SUNDAY";
//                case Calendar.MONDAY -> dayWeek = "MONDAY";
//                case Calendar.TUESDAY -> dayWeek = "TUESDAY";
//                case Calendar.WEDNESDAY -> dayWeek = "WEDNESDAY";
//                case Calendar.THURSDAY -> dayWeek = "THURSDAY";
//                case Calendar.FRIDAY -> dayWeek = "FRIDAY";
//                case Calendar.SATURDAY -> dayWeek = "SATURDAY";
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return dayWeek;
//    }

}
