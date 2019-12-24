package com.stock.exchange.value.object;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderTime {

    private Time time;

    public OrderTime(String strTime) {

        try{
            DateFormat dateFormat = new SimpleDateFormat("hh:mm");
            Date d = dateFormat.parse(strTime);

            Time time = new Time(d.getTime());

            this.time = time;

        }catch (Exception e){

        }
    }

    public Time getTime() {
        return time;
    }
}
