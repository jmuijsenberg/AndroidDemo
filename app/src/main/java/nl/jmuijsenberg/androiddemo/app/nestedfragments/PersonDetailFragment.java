package nl.jmuijsenberg.androiddemo.app.nestedfragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nl.jmuijsenberg.androiddemo.R;
import nl.jmuijsenberg.androiddemo.app.ApplicationExtension;
import nl.jmuijsenberg.androiddemo.app.binding.DataBinding;
import nl.jmuijsenberg.androiddemo.app.dialogs.DatePickerDialogFragment;
import nl.jmuijsenberg.androiddemo.util.android.datetime.DateTime;
import nl.jmuijsenberg.androiddemo.util.java.logging.Logger;
import nl.jmuijsenberg.androiddemo.viewmodels.base.OnPropertyFieldChanged;
import nl.jmuijsenberg.androiddemo.viewmodels.factory.ViewModelFactory;
import nl.jmuijsenberg.androiddemo.viewmodels.persons.ManagePersonsViewModel;
import nl.jmuijsenberg.androiddemo.viewmodels.persons.PersonViewModel;

public class PersonDetailFragment extends Fragment implements DatePickerDialog.OnDateSetListener{
    private static String TAG = "PersonDetailFragment";
    private static String FIRSTNAME = "FirstName";

    private OnFragmentInteractionListener mListener;

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

        ViewModelFactory viewModelFactory = ((ApplicationExtension) getContext().getApplicationContext()).getViewModelFactory();
        mManagePersonsViewModel = viewModelFactory.getManagePersonsViewModel();


        DataBinding.bindEditText(mFirstNameText, mManagePersonsViewModel.getSelectedPerson().get().getFirstName());
        DataBinding.bindEditText(mLastNameText, mManagePersonsViewModel.getSelectedPerson().get().getLastName());
        return view;
    }

    @SuppressWarnings({"squid:S00112"}) // Rethrow unchecked exception due to constraint in method signature
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) getParentFragment ();
        } catch (ClassCastException e) {
            Logger.e(TAG, e, "Parent fragemnt does not implement listener interface");
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
        mListener = null;
    }

    @OnClick(R.id.applyButton)
    public void onApply(Button button) {
        mManagePersonsViewModel.getAddPersonCommand().execute();

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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
}
