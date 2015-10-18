package nl.jmuijsenberg.androiddemo.app.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nl.jmuijsenberg.androiddemo.R;
import nl.jmuijsenberg.androiddemo.app.dialogs.ConfirmationDialogFragment;
import nl.jmuijsenberg.androiddemo.app.dialogs.DatePickerDialogFragment;
import nl.jmuijsenberg.androiddemo.app.dialogs.TimePickerDialogFragment;
import nl.jmuijsenberg.androiddemo.util.java.logging.Logger;

public class Fragment2 extends Fragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{
    private static String TAG = "Fragment2";

    @Bind(R.id.textView1)
    TextView textView1;
    @Bind(R.id.textView2)
    TextView textView2;
    private OnFragmentInteractionListener mListener;

    public Fragment2() {
        // Required empty public constructor
    }

    public static Fragment2 newInstance() {
        return new Fragment2();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment2, container, false);
        ButterKnife.bind(this, view);

        textView1.setText("fragment 2 text view 1 text");
        textView2.setText("fragment 2 text view 2 text");
        return view;
    }

    @SuppressWarnings({"squid:S00112"}) // Rethrow unchecked exception due to constraint in method signature
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            Logger.e(TAG, e, "Parent fragemnt does not implement listener interface");

            throw new Error(e);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.updateDateButton)
    public void updateDate(ImageButton button) {
        DatePickerDialogFragment dialog = new DatePickerDialogFragment();
        dialog.show(getChildFragmentManager(), "datePicker");
    }

    @OnClick(R.id.updateTimeButton)
    public void updateTime(ImageButton button) {
        TimePickerDialogFragment dialog = new TimePickerDialogFragment();
        dialog.show(getChildFragmentManager(), "timePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        ConfirmationDialogFragment dialog = ConfirmationDialogFragment.newInstance(R.string.date_changed, R.string.date_confirm, R.drawable.calendar_24p);
        dialog.show(getChildFragmentManager(), "confirm");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        ConfirmationDialogFragment dialog = ConfirmationDialogFragment.newInstance(R.string.time_changed, R.string.time_confirm, R.drawable.clock_24dp);
        dialog.show(getChildFragmentManager(), "confirm");
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
