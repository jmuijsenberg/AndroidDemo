package nl.jmuijsenberg.androiddemo.app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import nl.jmuijsenberg.androiddemo.R;
import nl.jmuijsenberg.androiddemo.app.ApplicationExtension;
import nl.jmuijsenberg.androiddemo.app.nestedfragments.StudentDetailFragment;
import nl.jmuijsenberg.androiddemo.app.nestedfragments.StudentListFragment;
import nl.jmuijsenberg.androiddemo.viewmodels.factory.ViewModelFactory;
import nl.jmuijsenberg.androiddemo.viewmodels.students.ManageStudentsViewModel;

public class StudentFragment extends Fragment {
    private ManageStudentsViewModel mManageStudentsViewModel;

    public StudentFragment() {
        // Required empty public constructor
    }

    public static StudentFragment newInstance() {
        return new StudentFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student, container, false);
        ButterKnife.bind(this, view);

        ViewModelFactory viewModelFactory = ((ApplicationExtension) getContext().getApplicationContext()).getViewModelFactory();
        mManageStudentsViewModel = viewModelFactory.getManageStudentsViewModel();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.studentListFrame, new StudentListFragment(), "tag1");
        transaction.add(R.id.studentDetailFrame, new StudentDetailFragment(), "tag2");
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

    public ManageStudentsViewModel getManageStudentsViewModel() {
        return mManageStudentsViewModel;
    }
}
