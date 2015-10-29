package nl.jmuijsenberg.androiddemo.app.nestedfragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nl.jmuijsenberg.androiddemo.R;
import nl.jmuijsenberg.androiddemo.app.adapters.PersonAdapter;
import nl.jmuijsenberg.androiddemo.app.adapters.RecyclerViewAdapterBase;
import nl.jmuijsenberg.androiddemo.app.dialogs.ExceptionDialogFragment;
import nl.jmuijsenberg.androiddemo.app.fragments.PersonFragment;
import nl.jmuijsenberg.androiddemo.entities.Person;
import nl.jmuijsenberg.androiddemo.util.java.logging.Logger;
import nl.jmuijsenberg.androiddemo.viewmodels.persons.ManagePersonsViewModel;

public class PersonListFragment extends Fragment implements ManagePersonsViewModel.PersonListListener {
    private static String TAG = "PersonListFragment";
    PersonAdapter mAdapter;
    LinearLayoutManager mLayoutManager;
    private ManagePersonsViewModel mManagePersonsViewModel;

    @Bind(R.id.personList)
    RecyclerView mPersonList;

    @Bind(R.id.addButton)
    Button mAddButton;
    @Bind(R.id.deleteButton)
    Button mDeleteButton;

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

        mAdapter = new PersonAdapter(new RecyclerViewAdapterBase.OnSelectionChangedListener<Person>() {
            @Override
            public void onSelectionChanged(Person person) {
                mManagePersonsViewModel.setSelectedPerson(person);
            }
        });
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
    public void onListChanged(List<Person> persons) {
        mAdapter.updatePersonList(persons);
    }

    @OnClick(R.id.addButton)
    public void onAdd(Button button) {
        mManagePersonsViewModel.newPerson();
    }

    @OnClick(R.id.deleteButton)
    public void onDelete(Button button) {
        mManagePersonsViewModel.deletePerson();
    }

    @Override
    public void onException(Throwable e) {
        ExceptionDialogFragment dialog = ExceptionDialogFragment.newInstance(e.getMessage(), e.getStackTrace().toString());
        dialog.show(getChildFragmentManager(), "exception");
    }
}
