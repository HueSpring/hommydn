package dn.hommy.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ProcessDay {

    public ProcessDay() {
    }

    //add date
    public String addDate(Date date, int days) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return sdf.format(c.getTime());
    }

    //split datetime -> get [year, month, day]
    public String[] splitDateTime(Date date) {
        String string = toStringDateTime(date);
        String[] s1 = string.split(" ");
        String[] s2 = s1[1].split("-");
        return s2;
    }

    
    
    
    
    
    //format datetime
    public String toStringDateTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    
    
}
