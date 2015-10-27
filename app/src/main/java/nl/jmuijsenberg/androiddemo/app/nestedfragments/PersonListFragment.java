package nl.jmuijsenberg.androiddemo.app.nestedfragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import nl.jmuijsenberg.androiddemo.R;
import nl.jmuijsenberg.androiddemo.app.ApplicationExtension;
import nl.jmuijsenberg.androiddemo.app.adapters.PersonAdapter;
import nl.jmuijsenberg.androiddemo.app.fragments.PersonFragment;
import nl.jmuijsenberg.androiddemo.entities.Gender;
import nl.jmuijsenberg.androiddemo.entities.Person;
import nl.jmuijsenberg.androiddemo.util.java.logging.Logger;
import nl.jmuijsenberg.androiddemo.viewmodels.factory.ViewModelFactory;
import nl.jmuijsenberg.androiddemo.viewmodels.persons.ManagePersonsViewModel;

public class PersonListFragment extends Fragment implements PersonAdapter.OnClickListener, ManagePersonsViewModel.PersonListListener {
    private static String TAG = "PersonListFragment";
    PersonAdapter mAdapter;
    LinearLayoutManager mLayoutManager;
    private ManagePersonsViewModel mManagePersonsViewModel;

    @Bind(R.id.personList)
    RecyclerView mPersonList;

    public PersonListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person_list, container, false);
        ButterKnife.bind(this, view);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mPersonList.setLayoutManager(mLayoutManager);

        mAdapter = new PersonAdapter(this);
        mPersonList.setAdapter(mAdapter);

        return view;
    }

    @SuppressWarnings({"squid:S00112"}) // Rethrow unchecked exception due to constraint in method signature
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            PersonFragment parentFragment = (PersonFragment) getParentFragment();
            mManagePersonsViewModel = parentFragment.getManagePersonsViewModel();
            mManagePersonsViewModel.attachListView(this);
        } catch (ClassCastException e) {
            Logger.e(TAG, e, "illegal cast");
            throw new Error(e);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mManagePersonsViewModel.detachListView();
        mManagePersonsViewModel = null;
    }

    @Override
    public void onPersonClick(Person p) {

    }

    @Override
    public void onListChanged(List<Person> persons) {
        mAdapter.updatePersonList(persons);
    }

    @Override
    public void onException(Throwable e) {

    }
}
