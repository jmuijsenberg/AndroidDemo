package nl.jmuijsenberg.androiddemo.app.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import nl.jmuijsenberg.androiddemo.R;
import nl.jmuijsenberg.androiddemo.app.ApplicationExtension;
import nl.jmuijsenberg.androiddemo.app.nestedfragments.PersonDetailFragment;
import nl.jmuijsenberg.androiddemo.app.nestedfragments.PersonListFragment;
import nl.jmuijsenberg.androiddemo.util.java.logging.Logger;
import nl.jmuijsenberg.androiddemo.viewmodels.factory.ViewModelFactory;
import nl.jmuijsenberg.androiddemo.viewmodels.persons.ManagePersonsViewModel;

public class PersonFragment extends Fragment {
    private static String TAG = "PersonFragment";

    private ManagePersonsViewModel mManagePersonsViewModel;

    public PersonFragment() {
        // Required empty public constructor
    }

    public static PersonFragment newInstance() {
        return new PersonFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        ButterKnife.bind(this, view);

        ViewModelFactory viewModelFactory = ((ApplicationExtension) getContext().getApplicationContext()).getViewModelFactory();
        mManagePersonsViewModel = viewModelFactory.getManagePersonsViewModel();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.personListFrame, new PersonListFragment(), "tag1");
        transaction.add(R.id.personDetailFrame, new PersonDetailFragment(), "tag2");
        transaction.commit();
        return view;
    }

    @SuppressWarnings({"squid:S00112"}) // Rethrow unchecked exception due to constraint in method signature
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public ManagePersonsViewModel getManagePersonsViewModel() {
        return mManagePersonsViewModel;
    }
}
