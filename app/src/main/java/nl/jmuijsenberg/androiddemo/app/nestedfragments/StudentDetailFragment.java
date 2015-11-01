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
import nl.jmuijsenberg.androiddemo.app.fragments.StudentFragment;
import nl.jmuijsenberg.androiddemo.util.android.datetime.DateTime;
import nl.jmuijsenberg.androiddemo.util.java.logging.Logger;
import nl.jmuijsenberg.androiddemo.viewmodels.students.ManageStudentsViewModel;

public class StudentDetailFragment extends Fragment implements DatePickerDialog.OnDateSetListener, ManageStudentsViewModel.StudentDetailListener{
    private static String TAG = "StudentDetailFragment";
    private static String FIRSTNAME = "FirstName";

    @Bind(R.id.studentFirstNameValue)
    EditText mFirstNameText;
    @Bind(R.id.studentLastNameValue)
    EditText mLastNameText;
    @Bind(R.id.studentDateOfBirthValue)
    TextView mDateOfBirthText;
    @Bind(R.id.saveButton)
    Button mSaveButton;
    @Bind(R.id.cancelButton)
    Button mCancelButton;

    private ManageStudentsViewModel mManageStudentsViewModel;

    public StudentDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_detail, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @SuppressWarnings({"squid:S00112"}) // Rethrow unchecked exception due to constraint in method signature
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            StudentFragment parentFragment = (StudentFragment) getParentFragment();
            mManageStudentsViewModel = parentFragment.getManageStudentsViewModel();
            mManageStudentsViewModel.attachDetailView(this);
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
        mManageStudentsViewModel.detachDetailView();
        mManageStudentsViewModel = null;
    }

    @OnTextChanged(R.id.studentFirstNameValue)
    public void onFirstNameTextChanged(CharSequence text) {
        mManageStudentsViewModel.getSelectedStudent().setFirstName(text.toString());
    }

    @OnTextChanged(R.id.studentLastNameValue)
    public void onLastNameTextChanged(CharSequence text) {
        mManageStudentsViewModel.getSelectedStudent().setLastName(text.toString());
    }

    @OnClick(R.id.studentDateOfBirthValue)
    public void onDateOfBirthSelect(TextView textView) {
        DatePickerDialogFragment dialog = new DatePickerDialogFragment();
        dialog.show(getChildFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        mDateOfBirthText.setText(DateTime.formatDate(year, monthOfYear, dayOfMonth));
    }

    @OnClick(R.id.saveButton)
    public void onSave(Button button) {
        mManageStudentsViewModel.saveStudent();
    }

    @OnClick(R.id.cancelButton)
    public void onCancel(Button button) {

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
        ExceptionDialogFragment dialog = ExceptionDialogFragment.newInstance(e);
        dialog.show(getChildFragmentManager(), "exception");
    }
}
