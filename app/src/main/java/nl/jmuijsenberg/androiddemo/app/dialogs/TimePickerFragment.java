package nl.jmuijsenberg.androiddemo.app.dialogs;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.view.ContextThemeWrapper;
import android.widget.TimePicker;

import java.util.Calendar;

import nl.jmuijsenberg.androiddemo.R;

// See http://stackoverflow.com/questions/28738089/change-datepicker-dialog-color-for-android-5-0
public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {
    private TimePickerDialog.OnTimeSetListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(new ContextThemeWrapper(getActivity(), R.style.AppTheme), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void setOnDateSetListener(TimePickerDialog.OnTimeSetListener listener)
    {
        mListener = listener;
    }

    @Override
    public void onTimeSet(TimePicker view, int hour, int minute) {
        if (mListener != null) {
            mListener.onTimeSet(view, hour, minute);
        }
    }


}
