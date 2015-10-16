package nl.jmuijsenberg.androiddemo.app.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

import nl.jmuijsenberg.androiddemo.R;

// See http://stackoverflow.com/questions/28738089/change-datepicker-dialog-color-for-android-5-0
public class DatePickerFragment extends android.support.v4.app.DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        DatePickerDialog dialog = new DatePickerDialog(new ContextThemeWrapper(getActivity(),R.style.AppTheme), this, year, month, day);

        dialog.getDatePicker().setMinDate(new Date(100,1,2).getTime());
        dialog.getDatePicker().setMaxDate(new Date(115,3,4).getTime());
        return dialog;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
    }
}
