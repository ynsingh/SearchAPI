package com.SearchAPI;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeStamp {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.HH.mm.ss.SSS");

    public static String currentTime() {

        //method 1
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String a = sdf.format(timestamp);
        return a;

    }
}
