package nl.jmuijsenberg.androiddemo.viewmodels.base;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateField extends PropertyField<Calendar> {
    private Calendar mMinimum;
    private Calendar mMaximum;

    public DateField(Calendar minimum, Calendar maximum, Calendar value, OnPropertyValidate onValidate)
    {
        super(value, onValidate);

        mMinimum = minimum;
        mMaximum = maximum;
    }

    public DateField(Calendar minimum, Calendar maximum, long valueInMilliSeconds, OnPropertyValidate onValidate)
    {
        super(convert(valueInMilliSeconds), onValidate);

        mMinimum = minimum;
        mMaximum = maximum;
    }

    public DateField(Calendar minimum, Calendar maximum, Calendar value)
    {
        super(value);
    }

    public void set(long dateInMilliSeconds)
    {
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(dateInMilliSeconds);
        setInternal(date);
    }

    public Calendar getMinimum()
    {
        return mMinimum;
    }

    public Calendar getMaximum()
    {
        return mMaximum;
    }

    private static Calendar convert(long timeInMilliSeconds)
    {
        Calendar dob = GregorianCalendar.getInstance();
        dob.setTimeInMillis(timeInMilliSeconds);
        return dob;
    }
}
