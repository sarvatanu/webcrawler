package com.qantas.webcrawler.api.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateUtils {

    private DateUtils() {
        throw new IllegalAccessError("Utility class");
    }

    public static String soapTimestamp() {
        TimeZone tz = TimeZone.getTimeZone("Australia/Sydney");
        GregorianCalendar cal = new GregorianCalendar(tz);
        Date dtCurrTime = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        return format.format(dtCurrTime);
    }
}
