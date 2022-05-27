package com.br.clinicregister.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateHelper {

    private GregorianCalendar gc;
    private Date dateForUse;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    public DateHelper(Date date){
        this.gc = new GregorianCalendar();
        this.gc.setTime(date);
        this.dateForUse = date;
    }

    public DateHelper(String data){
        try {
            this.gc = new GregorianCalendar();
            this.gc.setTime(formatter.parse(data));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Date getData(){
        return this.dateForUse;
    }

    public void addDays(int quantity){
        more(quantity, Calendar.DAY_OF_MONTH);
    }

    public void addMonths(int quantity){
        more(quantity, Calendar.MONTH);
    }

    public void addYears(int quantity){
        more(quantity, Calendar.YEAR);
    }

    private void more(int quantity, int fieldType){
        gc.add(fieldType, quantity);
        dateForUse = gc.getTime();
    }

    public int getMin(){
        return getField(Calendar.MINUTE);
    }

    public int getHour(){
        return getField(Calendar.HOUR);
    }

    private int getField(int fieldType){
        return gc.get(fieldType);
    }

    public Integer compare(String data){
        try {
            return compare(formatter.parse(data));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public int compare(Date data){
        Calendar c = new GregorianCalendar();
        c.setTime(data);
        return gc.compareTo(c);
    }

    public String getAsString(){
        return formatter.format(dateForUse);
    }

}