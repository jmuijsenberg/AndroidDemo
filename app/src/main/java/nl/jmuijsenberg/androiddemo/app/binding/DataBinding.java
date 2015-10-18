package nl.jmuijsenberg.androiddemo.app.binding;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import nl.jmuijsenberg.androiddemo.viewmodels.base.Command;
import nl.jmuijsenberg.androiddemo.viewmodels.base.DateField;
import nl.jmuijsenberg.androiddemo.viewmodels.base.OnPropertyFieldChanged;
import nl.jmuijsenberg.androiddemo.viewmodels.base.PropertyField;

public class DataBinding {
    public static void bindEditText(final EditText editText , final PropertyField<String> text) {
        if (editText != null) {
            editText.setText(text.get());
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    text.setui(s.toString());
                }
            });

            text.addObserver(new OnPropertyFieldChanged<String>() {
                @Override
                public void onPropertyChanged(String value) {
                    editText.setText(value);
                }
            });
        }
    }

    public static void bindTextView(final View parent, final TextView textView, final PropertyField<Integer> resourceId) {
        if (textView != null) {
            resourceId.addObserver(new OnPropertyFieldChanged<Integer>() {
                @Override
                public void onPropertyChanged(Integer value) {
                    textView.setText(parent.getResources().getString(value));
                }
            });
        }
    }

    public static void bindDatePicker(final View parent, final DatePicker datePicker, final DateField date) {
        if (datePicker != null) {
            datePicker.setMaxDate(date.getMaximum().getTimeInMillis());
            datePicker.setMinDate(date.getMinimum().getTimeInMillis());
            datePicker.init(date.get().get(Calendar.YEAR), date.get().get(Calendar.MONTH), date.get().get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    if ((year != date.get().get(Calendar.YEAR)) ||
                            (monthOfYear != date.get().get(Calendar.MONTH)) ||
                            (dayOfMonth != date.get().get(Calendar.DAY_OF_MONTH))) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, monthOfYear, dayOfMonth);
                        date.setui(calendar);
                    }
                }
            });

            date.addObserver(new OnPropertyFieldChanged<Calendar>() {
                @Override
                public void onPropertyChanged(Calendar value) {
                    datePicker.updateDate(value.get(Calendar.YEAR), value.get(Calendar.MONTH), value.get(Calendar.DAY_OF_MONTH));
                }
            });
        }
    }

    public static void bindButton(final View parent, final Button button, final Command command) {
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    command.execute();
                }
            });

            command.canExecute.addObserver(new OnPropertyFieldChanged<Boolean>() {
                @Override
                public void onPropertyChanged(Boolean value) {
                    button.setEnabled(value);
                }
            });
        }
    }
}
