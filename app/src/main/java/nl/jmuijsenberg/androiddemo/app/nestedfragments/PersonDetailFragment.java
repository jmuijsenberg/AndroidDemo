package nl.jmuijsenberg.androiddemo.app.nestedfragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import nl.jmuijsenberg.androiddemo.R;
import nl.jmuijsenberg.androiddemo.app.dialogs.DatePickerDialogFragment;
import nl.jmuijsenberg.androiddemo.app.dialogs.ExceptionDialogFragment;
import nl.jmuijsenberg.androiddemo.app.dialogs.LoginDialogFragment;
import nl.jmuijsenberg.androiddemo.app.fragments.PersonFragment;
import nl.jmuijsenberg.androiddemo.util.android.datetime.DateTime;
import nl.jmuijsenberg.androiddemo.util.java.logging.Logger;
import nl.jmuijsenberg.androiddemo.viewmodels.persons.ManagePersonsViewModel;

public class PersonDetailFragment extends Fragment implements DatePickerDialog.OnDateSetListener, ManagePersonsViewModel.PersonDetailListener{
    private static String TAG = "PersonDetailFragment";
    private static String FIRSTNAME = "FirstName";

    @Bind(R.id.personFirstNameValue)
    EditText mFirstNameText;
    @Bind(R.id.personLastNameValue)
    EditText mLastNameText;
    @Bind(R.id.personDateOfBirthValue)
    TextView mDateOfBirthText;
    @Bind(R.id.applyButton)
    Button mApplyButton;

    private ManagePersonsViewModel mManagePersonsViewModel;

    public PersonDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person_detail, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @SuppressWarnings({"squid:S00112"}) // Rethrow unchecked exception due to constraint in method signature
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            PersonFragment parentFragment = (PersonFragment) getParentFragment();
            mManagePersonsViewModel = parentFragment.getManagePersonsViewModel();
            mManagePersonsViewModel.attachDetailView(this);
        } catch (ClassCastException e) {
            Logger.e(TAG, e, "illegal cast");
            throw new Error(e);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putString(FIRSTNAME, mFirstNameText.getText().toString());

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mManagePersonsViewModel.detachDetailView();
        mManagePersonsViewModel = null;
    }

    @OnTextChanged(R.id.personFirstNameValue)
    public void onFirstNameTextChanged(CharSequence text) {
        mManagePersonsViewModel.setFirstName(text.toString());
    }

    @OnTextChanged(R.id.personLastNameValue)
    public void onLastNameTextChanged(CharSequence text) {
        mManagePersonsViewModel.setLastName(text.toString());
    }

    @OnClick(R.id.personDateOfBirthValue)
    public void onDateOfBirthSelect(TextView textView) {
        DatePickerDialogFragment dialog = new DatePickerDialogFragment();
        dialog.show(getChildFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        mDateOfBirthText.setText(DateTime.formatDate(year, monthOfYear, dayOfMonth));
    }

    @OnClick(R.id.applyButton)
    public void onApply(Button button) {
        mManagePersonsViewModel.addPerson();
    }

    @Override
    public void onFirstNameChanged(String firstName) {
        mFirstNameText.setText(firstName);
    }

    @Override
    public void onLastNameChanged(String lastName) {
        mLastNameText.setText(lastName);
    }

    @Override
    public void onGenderChanged(String gender) {

    }

    @Override
    public void onDateOfBirthChanged(String dateOfBirth) {

    }

    @Override
    public void onException(Throwable e) {
        ExceptionDialogFragment dialog = ExceptionDialogFragment.newInstance(e.getMessage(), e.getStackTrace().toString());
        dialog.show(getChildFragmentManager(), "exception");
    }
}
