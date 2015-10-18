package nl.jmuijsenberg.androiddemo.util.android.datetime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTime {
    private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MMM-dd");

    public static String formatDate(int year, int month, int day)
    {
        Calendar calendar = new GregorianCalendar(year, month, day);
        Date date = calendar.getTime();
        return  DATE_FORMAT.format(date);
    }
}
