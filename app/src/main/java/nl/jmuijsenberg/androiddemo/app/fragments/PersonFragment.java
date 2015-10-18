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
import nl.jmuijsenberg.androiddemo.app.nestedfragments.PersonDetailFragment;
import nl.jmuijsenberg.androiddemo.app.nestedfragments.PersonListFragment;
import nl.jmuijsenberg.androiddemo.util.java.logging.Logger;

public class PersonFragment extends Fragment implements PersonListFragment.OnFragmentInteractionListener, PersonDetailFragment.OnFragmentInteractionListener {
    private static String TAG = "PersonFragment";

    private OnFragmentInteractionListener mListener;

    public static PersonFragment newInstance() {
        return new PersonFragment();
    }

    public PersonFragment() {
        // Required empty public constructor
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

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.personListFrame, new PersonListFragment(), "tag1");
        transaction.add(R.id.personDetailFrame, new PersonDetailFragment(), "tag2");
        transaction.commit();
        return view;
    }

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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
