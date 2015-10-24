package nl.jmuijsenberg.androiddemo.util.java.datetime;

import java.util.Calendar;

public class DateTimeUtil {
    public static Calendar calendarFromMilliSeconds(long milliSeconds)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(milliSeconds);
        return cal;
    }
}
