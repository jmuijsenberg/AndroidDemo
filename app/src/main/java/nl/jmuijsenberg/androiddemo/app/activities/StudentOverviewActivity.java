package nl.jmuijsenberg.androiddemo.app.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nl.jmuijsenberg.androiddemo.R;
import nl.jmuijsenberg.androiddemo.app.ApplicationExtension;
import nl.jmuijsenberg.androiddemo.app.adapters.RecyclerViewAdapterBase;
import nl.jmuijsenberg.androiddemo.app.adapters.StudentListAdapter;
import nl.jmuijsenberg.androiddemo.entities.Gender;
import nl.jmuijsenberg.androiddemo.entities.Student;
import nl.jmuijsenberg.androiddemo.viewmodels.factory.ViewModelFactory;
import nl.jmuijsenberg.androiddemo.viewmodels.students.StudentOverviewViewModel;

public class StudentOverviewActivity extends AppCompatActivity implements StudentOverviewViewModel.StudentOverviewListener {
    private StudentListAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private StudentOverviewViewModel mViewModel;

    @Bind(R.id.fab)
    public FloatingActionButton mFloatingActionButton;
    @Bind(R.id.toolbar)
    public Toolbar mToolbar;
    @Bind(R.id.studentList)
    public RecyclerView mStudentRecyclerView;

    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, StudentOverviewActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_overview);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mLayoutManager = new LinearLayoutManager(this);
        mStudentRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new StudentListAdapter(new RecyclerViewAdapterBase.OnSelectionChangedListener<Student>() {
            @Override
            public void onSelectionChanged(final Student student) {
                editStudent(student);
            }
        });
        mStudentRecyclerView.setAdapter(mAdapter);

        ViewModelFactory viewModelFactory = ((ApplicationExtension) getApplicationContext()).getViewModelFactory();
        mViewModel = viewModelFactory.getStudentOverviewViewModel();
        mViewModel.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.detachView();
    }

    @OnClick(R.id.fab)
    public void addStudent() {
        startActivity(StudentEditActivity.getCallingIntent(this));
    }

    private void editStudent(final Student student) {
        startActivity(StudentEditActivity.getCallingIntent(this, student));
    }

    @Override
    public void onListChanged(List<Student> students) {
        mAdapter.updateList(students);
    }

    @Override
    public void onException(Throwable e) {

    }
}
