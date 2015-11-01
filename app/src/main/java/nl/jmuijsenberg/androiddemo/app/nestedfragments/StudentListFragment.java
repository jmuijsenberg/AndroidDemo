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
import nl.jmuijsenberg.androiddemo.app.adapters.RecyclerViewAdapterBase;
import nl.jmuijsenberg.androiddemo.app.adapters.StudentListAdapter;
import nl.jmuijsenberg.androiddemo.app.dialogs.ExceptionDialogFragment;
import nl.jmuijsenberg.androiddemo.app.fragments.StudentFragment;
import nl.jmuijsenberg.androiddemo.entities.Student;
import nl.jmuijsenberg.androiddemo.util.java.logging.Logger;
import nl.jmuijsenberg.androiddemo.viewmodels.students.ManageStudentsViewModel;

public class StudentListFragment extends Fragment implements ManageStudentsViewModel.StudentListListener {
    private static String TAG = "PersonListFragment";
    StudentListAdapter mAdapter;
    LinearLayoutManager mLayoutManager;
    private ManageStudentsViewModel mManageStudentsViewModel;

    @Bind(R.id.studentList)
    RecyclerView mStudentList;

    @Bind(R.id.addButton)
    Button mAddButton;
    @Bind(R.id.deleteButton)
    Button mDeleteButton;

    public StudentListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_list, container, false);
        ButterKnife.bind(this, view);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mStudentList.setLayoutManager(mLayoutManager);

        mAdapter = new StudentListAdapter(new RecyclerViewAdapterBase.OnSelectionChangedListener<Student>() {
            @Override
            public void onSelectionChanged(Student student) {
                mManageStudentsViewModel.setSelectedStudent(student);
            }
        });
        mStudentList.setAdapter(mAdapter);

        return view;
    }

    @SuppressWarnings({"squid:S00112"}) // Rethrow unchecked exception due to constraint in method signature
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            StudentFragment parentFragment = (StudentFragment) getParentFragment();
            mManageStudentsViewModel = parentFragment.getManageStudentsViewModel();
            mManageStudentsViewModel.attachListView(this);
        } catch (ClassCastException e) {
            Logger.e(TAG, e, "illegal cast");
            throw new Error(e);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mManageStudentsViewModel.detachListView();
        mManageStudentsViewModel = null;
    }

    @Override
    public void onListChanged(List<Student> students) {
        mAdapter.updateList(students);
    }

    @OnClick(R.id.addButton)
    public void onAdd() {
        mManageStudentsViewModel.newStudent();
    }

    @OnClick(R.id.deleteButton)
    public void onDelete() {
        mManageStudentsViewModel.deleteStudent();
    }

    @Override
    public void onException(Throwable e) {
        ExceptionDialogFragment dialog = ExceptionDialogFragment.newInstance(e);
        dialog.show(getChildFragmentManager(), "exception");
    }
}
