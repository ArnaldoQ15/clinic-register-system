package com.br.clinicregistersystem.model;

import com.br.clinicregistersystem.domain.repository.ConsultRepository;
import com.br.clinicregistersystem.exception.BusinessException;
import com.br.clinicregistersystem.exception.EntityNotFoundException;
import lombok.Getter;

import javax.print.Doc;
import java.util.ArrayList;

@Getter
public enum DoctorScalesEspeciality {

    /* Convention:
    *
    ~> A               M             M              08           00
    ~> Angiology       Monday        Morning        Hours        Minutes
    *
    */

    //
    // ANGIOLOGY
    //

    AMM0800("08:00 AM", "Monday", "Angiology"),
    AMM0830("08:30 AM", "Monday", "Angiology"),
    AMM0900("09:00 AM", "Monday", "Angiology"),
    AMM0930("09:30 AM", "Monday", "Angiology"),
    AMM1000("10:00 AM", "Monday", "Angiology"),
    AMM1030("10:30 AM", "Monday", "Angiology"),
    AMM1100("11:00 AM", "Monday", "Angiology"),
    AMM1130("11:30 AM", "Monday", "Angiology"),
    AMA1400("14:00 PM", "Monday", "Angiology"),
    AMA1430("14:30 PM", "Monday", "Angiology"),
    AMA1500("15:00 PM", "Monday", "Angiology"),
    AMA1530("15:30 PM", "Monday", "Angiology"),
    AMA1600("16:00 PM", "Monday", "Angiology"),
    AMA1630("16:30 PM", "Monday", "Angiology"),
    AMA1700("17:00 PM", "Monday", "Angiology"),
    AMA1730("17:30 PM", "Monday", "Angiology"),
    ASSM0800("08:00 AM", "Saturday", "Angiology"),
    ASSM0830("08:30 AM", "Saturday", "Angiology"),
    ASM0900("09:00 AM", "Saturday", "Angiology"),
    ASM0930("09:30 AM", "Saturday", "Angiology"),
    ASM1000("10:00 AM", "Saturday", "Angiology"),
    ASM1030("10:30 AM", "Saturday", "Angiology"),
    ASM1100("11:00 AM", "Saturday", "Angiology"),
    ASM1130("11:30 AM", "Saturday", "Angiology"),



    //
    // CARDIOLOGY
    //


    CTM0800("08:00 AM", "Tuesday", "Cardiology"),
    CTM0830("08:30 AM", "Tuesday", "Cardiology"),
    CTM0900("09:00 AM", "Tuesday", "Cardiology"),
    CTM0930("09:30 AM", "Tuesday", "Cardiology"),
    CTM1000("10:00 AM", "Tuesday", "Cardiology"),
    CTM1030("10:30 AM", "Tuesday", "Cardiology"),
    CTM1100("11:00 AM", "Tuesday", "Cardiology"),
    CTM1130("11:30 AM", "Tuesday", "Cardiology"),
    CTA1400("14:00 PM", "Tuesday", "Cardiology"),
    CTA1430("14:30 PM", "Tuesday", "Cardiology"),
    CTA1500("15:00 PM", "Tuesday", "Cardiology"),
    CTA1530("15:30 PM", "Tuesday", "Cardiology"),
    CTA1600("16:00 PM", "Tuesday", "Cardiology"),
    CTA1630("16:30 PM", "Tuesday", "Cardiology"),
    CTA1700("17:00 PM", "Tuesday", "Cardiology"),
    CTA1730("17:30 PM", "Tuesday", "Cardiology"),
    CTN1800("18:00 PM", "Tuesday", "Cardiology"),
    CTN1815("18:15 PM", "Tuesday", "Cardiology"),
    CTN1830("18:30 PM", "Tuesday", "Cardiology"),
    CTN1845("18:45 PM", "Tuesday", "Cardiology"),
    CTN1900("19:00 PM", "Tuesday", "Cardiology"),
    CTN1915("19:15 PM", "Tuesday", "Cardiology"),
    CTN1930("19:30 PM", "Tuesday", "Cardiology"),
    CTN1945("19:45 PM", "Tuesday", "Cardiology"),
    CWA1400("14:00 PM", "Wednesday", "Cardiology"),
    CWA1430("14:30 PM", "Wednesday", "Cardiology"),
    CWA1500("15:00 PM", "Wednesday", "Cardiology"),
    CWA1530("15:30 PM", "Wednesday", "Cardiology"),
    CWA1600("16:00 PM", "Wednesday", "Cardiology"),
    CWA1630("16:30 PM", "Wednesday", "Cardiology"),
    CWA1700("17:00 PM", "Wednesday", "Cardiology"),
    CWA1730("17:30 PM", "Wednesday", "Cardiology"),



    //
    // MEDICAL CLINIC
    //


    MCMA1400("14:00 PM", "Monday", "Medical Clinic"),
    MCMA1430("14:30 PM", "Monday", "Medical Clinic"),
    MCMA1500("15:00 PM", "Monday", "Medical Clinic"),
    MCMA1530("15:30 PM", "Monday", "Medical Clinic"),
    MCMA1600("16:00 PM", "Monday", "Medical Clinic"),
    MCMA1630("16:30 PM", "Monday", "Medical Clinic"),
    MCMA1700("17:00 PM", "Monday", "Medical Clinic"),
    MCMA1730("17:30 PM", "Monday", "Medical Clinic"),
    MCMN1800("18:00 PM", "Monday", "Medical Clinic"),
    MCMN1815("18:15 PM", "Monday", "Medical Clinic"),
    MCMN1830("18:30 PM", "Monday", "Medical Clinic"),
    MCMN1845("18:45 PM", "Monday", "Medical Clinic"),
    MCMN1900("19:00 PM", "Monday", "Medical Clinic"),
    MCMN1915("19:15 PM", "Monday", "Medical Clinic"),
    MCMN1930("19:30 PM", "Monday", "Medical Clinic"),
    MCMN1945("19:45 PM", "Monday", "Medical Clinic"),
    MCTM0800("08:00 AM", "Thursday", "Medical Clinic"),
    MCTM0830("08:30 AM", "Thursday", "Medical Clinic"),
    MCTM0900("09:00 AM", "Thursday", "Medical Clinic"),
    MCTM0930("09:30 AM", "Thursday", "Medical Clinic"),
    MCTM1000("10:00 AM", "Thursday", "Medical Clinic"),
    MCTM1030("10:30 AM", "Thursday", "Medical Clinic"),
    MCTM1100("11:00 AM", "Thursday", "Medical Clinic"),
    MCTM1130("11:30 AM", "Thursday", "Medical Clinic"),
    MCTA1400("14:00 PM", "Thursday", "Medical Clinic"),
    MCTA1430("14:30 PM", "Thursday", "Medical Clinic"),
    MCTA1500("15:00 PM", "Thursday", "Medical Clinic"),
    MCTA1530("15:30 PM", "Thursday", "Medical Clinic"),
    MCTA1600("16:00 PM", "Thursday", "Medical Clinic"),
    MCTA1630("16:30 PM", "Thursday", "Medical Clinic"),
    MCTA1700("17:00 PM", "Thursday", "Medical Clinic"),
    MCTA1730("17:30 PM", "Thursday", "Medical Clinic"),
    MCTN1800("18:00 PM", "Thursday", "Medical Clinic"),
    MCTN1815("18:15 PM", "Thursday", "Medical Clinic"),
    MCTN1830("18:30 PM", "Thursday", "Medical Clinic"),
    MCTN1845("18:45 PM", "Thursday", "Medical Clinic"),
    MCTN1900("19:00 PM", "Thursday", "Medical Clinic"),
    MCTN1915("19:15 PM", "Thursday", "Medical Clinic"),
    MCTN1930("19:30 PM", "Thursday", "Medical Clinic"),
    MCTN1945("19:45 PM", "Thursday", "Medical Clinic"),



    //
    // DERMATOLOGY
    //


    DTM0800("08:00 AM", "Thursday", "Dermatology"),
    DTM0830("08:30 AM", "Thursday", "Dermatology"),
    DTM0900("09:00 AM", "Thursday", "Dermatology"),
    DTM0930("09:30 AM", "Thursday", "Dermatology"),
    DTM1000("10:00 AM", "Thursday", "Dermatology"),
    DTM1030("10:30 AM", "Thursday", "Dermatology"),
    DTM1100("11:00 AM", "Thursday", "Dermatology"),
    DTM1130("11:30 AM", "Thursday", "Dermatology"),
    DFM0800("08:00 AM", "Friday", "Dermatology"),
    DFM0830("08:30 AM", "Friday", "Dermatology"),
    DFM0900("09:00 AM", "Friday", "Dermatology"),
    DFM0930("09:30 AM", "Friday", "Dermatology"),
    DFM1000("10:00 AM", "Friday", "Dermatology"),
    DFM1030("10:30 AM", "Friday", "Dermatology"),
    DFM1100("11:00 AM", "Friday", "Dermatology"),
    DFM1130("11:30 AM", "Friday", "Dermatology"),
    DFA1400("14:00 PM", "Friday", "Dermatology"),
    DFA1430("14:30 PM", "Friday", "Dermatology"),
    DFA1500("15:00 PM", "Friday", "Dermatology"),
    DFA1530("15:30 PM", "Friday", "Dermatology"),
    DFA1600("16:00 PM", "Friday", "Dermatology"),
    DFA1630("16:30 PM", "Friday", "Dermatology"),
    DFA1700("17:00 PM", "Friday", "Dermatology"),
    DFA1730("17:30 PM", "Friday", "Dermatology"),
    DFN1800("18:00 PM", "Friday", "Dermatology"),
    DFN1815("18:15 PM", "Friday", "Dermatology"),
    DFN1830("18:30 PM", "Friday", "Dermatology"),
    DFN1845("18:45 PM", "Friday", "Dermatology"),
    DFN1900("19:00 PM", "Friday", "Dermatology"),
    DFN1915("19:15 PM", "Friday", "Dermatology"),
    DFN1930("19:30 PM", "Friday", "Dermatology"),
    DFN1945("19:45 PM", "Friday", "Dermatology"),



    //
    // SPEECH THERAPY
    //


    STWM0800("08:00 AM", "Wednesday", "Speech Therapy"),
    STWM0830("08:30 AM", "Wednesday", "Speech Therapy"),
    STWM0900("09:00 AM", "Wednesday", "Speech Therapy"),
    STWM0930("09:30 AM", "Wednesday", "Speech Therapy"),
    STWM1000("10:00 AM", "Wednesday", "Speech Therapy"),
    STWM1030("10:30 AM", "Wednesday", "Speech Therapy"),
    STWM1100("11:00 AM", "Wednesday", "Speech Therapy"),
    STWM1130("11:30 AM", "Wednesday", "Speech Therapy"),
    STTA1400("14:00 PM", "Thursday", "Speech Therapy"),
    STTA1430("14:30 PM", "Thursday", "Speech Therapy"),
    STTA1500("15:00 PM", "Thursday", "Speech Therapy"),
    STTA1530("15:30 PM", "Thursday", "Speech Therapy"),
    STTA1600("16:00 PM", "Thursday", "Speech Therapy"),
    STTA1630("16:30 PM", "Thursday", "Speech Therapy"),
    STTA1700("17:00 PM", "Thursday", "Speech Therapy"),
    STTA1730("17:30 PM", "Thursday", "Speech Therapy"),
    STTN1800("18:00 PM", "Thursday", "Speech Therapy"),
    STTN1815("18:15 PM", "Thursday", "Speech Therapy"),
    STTN1830("18:30 PM", "Thursday", "Speech Therapy"),
    STTN1845("18:45 PM", "Thursday", "Speech Therapy"),
    STTN1900("19:00 PM", "Thursday", "Speech Therapy"),
    STTN1915("19:15 PM", "Thursday", "Speech Therapy"),
    STTN1930("19:30 PM", "Thursday", "Speech Therapy"),
    STTN1945("19:45 PM", "Thursday", "Speech Therapy"),



    //
    // GASTROENTEROLOGY
    //


    GMM0800("08:00 AM", "Monday", "Gastroenterology"),
    GMM0830("08:30 AM", "Monday", "Gastroenterology"),
    GMM0900("09:00 AM", "Monday", "Gastroenterology"),
    GMM0930("09:30 AM", "Monday", "Gastroenterology"),
    GMM1000("10:00 AM", "Monday", "Gastroenterology"),
    GMM1030("10:30 AM", "Monday", "Gastroenterology"),
    GMM1100("11:00 AM", "Monday", "Gastroenterology"),
    GMM1130("11:30 AM", "Monday", "Gastroenterology"),
    GMA1400("14:00 PM", "Monday", "Gastroenterology"),
    GMA1430("14:30 PM", "Monday", "Gastroenterology"),
    GMA1500("15:00 PM", "Monday", "Gastroenterology"),
    GMA1530("15:30 PM", "Monday", "Gastroenterology"),
    GMA1600("16:00 PM", "Monday", "Gastroenterology"),
    GMA1630("16:30 PM", "Monday", "Gastroenterology"),
    GMA1700("17:00 PM", "Monday", "Gastroenterology"),
    GMA1730("17:30 PM", "Monday", "Gastroenterology"),
    GMN1800("18:00 PM", "Monday", "Gastroenterology"),
    GMN1815("18:15 PM", "Monday", "Gastroenterology"),
    GMN1830("18:30 PM", "Monday", "Gastroenterology"),
    GMN1845("18:45 PM", "Monday", "Gastroenterology"),
    GMN1900("19:00 PM", "Monday", "Gastroenterology"),
    GMN1915("19:15 PM", "Monday", "Gastroenterology"),
    GMN1930("19:30 PM", "Monday", "Gastroenterology"),
    GMN1945("19:45 PM", "Monday", "Gastroenterology"),
    GFM0800("08:00 AM", "Monday", "Gastroenterology"),
    GFM0830("08:30 AM", "Monday", "Gastroenterology"),
    GFM0900("09:00 AM", "Monday", "Gastroenterology"),
    GFM0930("09:30 AM", "Monday", "Gastroenterology"),
    GFM1000("10:00 AM", "Monday", "Gastroenterology"),
    GFM1030("10:30 AM", "Monday", "Gastroenterology"),
    GFM1100("11:00 AM", "Monday", "Gastroenterology"),
    GFM1130("11:30 AM", "Monday", "Gastroenterology");



    private final String hour;
    private final String day;
    private final String especiality;


    DoctorScalesEspeciality(String hour, String day, String especiality) {
        this.hour = hour;
        this.day = day;
        this.especiality = especiality;
    }

}


/*
    M0800("08:00 AM", "Monday", "angiology"),
    M0830("08:30 AM", "Monday", "angiology"),
    M0900("09:00 AM", "Monday", "angiology"),
    M0930("09:30 AM", "Monday", "angiology"),
    M1000("10:00 AM", "Monday", "angiology"),
    M1030("10:30 AM", "Monday", "angiology"),
    M1100("11:00 AM", "Monday", "angiology"),
    M1130("11:30 AM", "Monday", "angiology"),
    A1400("14:00 PM", "Monday", "angiology"),
    A1430("14:30 PM", "Monday", "angiology"),
    A1500("15:00 PM", "Monday", "angiology"),
    A1530("15:30 PM", "Monday", "angiology"),
    A1600("16:00 PM", "Monday", "angiology"),
    A1630("16:30 PM", "Monday", "angiology"),
    A1700("17:00 PM", "Monday", "angiology"),
    A1730("17:30 PM", "Monday", "angiology"),
    N1800("18:00 PM", "Tuesday", "cardiology"),
    N1815("18:15 PM", "Tuesday", "cardiology"),
    N1830("18:30 PM", "Tuesday", "cardiology"),
    N1845("18:45 PM", "Tuesday", "cardiology"),
    N1900("19:00 PM", "Tuesday", "cardiology"),
    N1915("19:15 PM", "Tuesday", "cardiology"),
    N1930("19:30 PM", "Tuesday", "cardiology"),
    N1945("19:45 PM", "Tuesday", "cardiology"),

 */