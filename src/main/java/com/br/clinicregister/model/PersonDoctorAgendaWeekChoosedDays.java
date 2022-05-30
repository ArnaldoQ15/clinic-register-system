package com.br.clinicregister.model;

import lombok.Getter;

@Getter
public enum PersonDoctorAgendaWeekChoosedDays {

    /* Regras:
    * Não pode trabalhar 2 dias seguidos.
    * Necessário haver pausa mínima de 24h antes de novo expediente.
    * Mínimo 2 expedientes por semana.
    * Máximo 3 expedientes por semana.
    * Se tiver 3 expedientes em uma semana, não pode trabalhar em 3 turnos em qualquer dos dias.
    * Se pegar 2 expedientes em uma semana, necessário escolher 1 dia para trabalhar todos os turnos.
     */

    MON_WED("Monday and Wednesday", 1),
    MON_WED_FRI("Monday, Wednesday and Friday", 2),
    TUES_THUR("Tuesday and Thursday", 3),
    TUES_THUR_SAT("Tuesday and Thursday", 4),
    WED_FRI("Wednesday and Friday", 5),
    THUR_SAT("Thursday and Saturday", 6),
    MON_FRI("Monday and Friday", 7),
    MON_SAT("Monday and Saturday", 8),
    MON_THUR("Monday and Thursday", 9),
    MON_THUR_SAT("Monday, Thursday and Saturday", 10),
    TUES_FRI("Tuesday and Friday", 11),
    TUES_SAT("Tuesday and Saturday", 12),
    WED_SAT("Wednesday and Saturday", 13),
    MON_WED_SAT("Monday, Wednesday and Saturday", 14);

    private final String scaleDays;

    private final Integer identifier;

    PersonDoctorAgendaWeekChoosedDays(String scaleDays, Integer identifier) {

        this.scaleDays = scaleDays;
        this.identifier = identifier;
    }

}
