package nl.jmuijsenberg.androiddemo.app.dialogs;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.view.ContextThemeWrapper;

import java.util.Calendar;

import nl.jmuijsenberg.androiddemo.R;

public class TimePickerFragment extends DialogFragment
{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog.OnTimeSetListener listener = (TimePickerDialog.OnTimeSetListener) getParentFragment();
        return new TimePickerDialog(new ContextThemeWrapper(getActivity(), R.style.AppTheme), listener, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }
}
