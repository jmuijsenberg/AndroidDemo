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
import nl.jmuijsenberg.androiddemo.app.adapters.CourseListAdapter;
import nl.jmuijsenberg.androiddemo.app.adapters.RecyclerViewAdapterBase;
import nl.jmuijsenberg.androiddemo.app.adapters.StudentListAdapter;
import nl.jmuijsenberg.androiddemo.entities.Course;
import nl.jmuijsenberg.androiddemo.entities.Gender;
import nl.jmuijsenberg.androiddemo.entities.Student;

public class StudentOverviewActivity extends AppCompatActivity {
    private StudentListAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

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

        List<Student> students = new ArrayList<>();
        Student student1 = new Student();
        student1.setFirstName("Johan");
        student1.setLastName("vdM");
        student1.setGender(Gender.MALE);
        students.add(student1);

        Student student2 = new Student();
        student2.setFirstName("Johan");
        student2.setLastName("vdM");
        student2.setGender(Gender.MALE);
        students.add(student2);

        mAdapter.updateList(students);
    }

    @OnClick(R.id.fab)
    public void addStudent() {
        startActivity(StudentEditActivity.getCallingIntent(this));
    }

    private void editStudent(final Student student) {
        startActivity(StudentEditActivity.getCallingIntent(this, student));
    }
}
