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

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import nl.jmuijsenberg.androiddemo.R;
import nl.jmuijsenberg.androiddemo.app.adapters.PersonAdapter;
import nl.jmuijsenberg.androiddemo.entities.Gender;
import nl.jmuijsenberg.androiddemo.entities.Person;
import nl.jmuijsenberg.androiddemo.util.java.logging.Logger;

public class PersonListFragment extends Fragment implements PersonAdapter.OnClickListener {
    private static String TAG = "PersonListFragment";
    PersonAdapter mAdapter;
    LinearLayoutManager mLayoutManager;

    @Bind(R.id.personList)
    RecyclerView mPersonList;

    private OnFragmentInteractionListener mListener;

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

        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Johan", "M", Gender.MALE, new GregorianCalendar(1960, 5, 11)));
        persons.add(new Person("Jeanette", "R", Gender.FEMALE, new GregorianCalendar(1900, 2, 3)));
        mAdapter.updatePersonList(persons);
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
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPersonClick(Person p) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
}
