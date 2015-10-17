package nl.jmuijsenberg.androiddemo.app.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.ContextThemeWrapper;

import java.util.Calendar;
import java.util.Date;

import nl.jmuijsenberg.androiddemo.R;

public class DatePickerFragment extends DialogFragment
{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener listener = (DatePickerDialog.OnDateSetListener) getParentFragment();
        DatePickerDialog dialog = new DatePickerDialog(new ContextThemeWrapper(getActivity(),R.style.AppTheme), listener, year, month, day);

        dialog.getDatePicker().setMinDate(new Date(100,1,2).getTime());
        dialog.getDatePicker().setMaxDate(new Date(115,3,4).getTime());
        return dialog;
    }
}
