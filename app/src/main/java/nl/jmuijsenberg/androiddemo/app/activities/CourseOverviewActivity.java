package nl.jmuijsenberg.androiddemo.app.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nl.jmuijsenberg.androiddemo.R;
import nl.jmuijsenberg.androiddemo.app.adapters.CourseListAdapter;
import nl.jmuijsenberg.androiddemo.app.adapters.RecyclerViewAdapterBase;
import nl.jmuijsenberg.androiddemo.entities.Course;

public class CourseOverviewActivity extends AppCompatActivity {
    private CourseListAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    @Bind(R.id.fab)
    public FloatingActionButton mFloatingActionButton;

    @Bind(R.id.toolbar)
    public Toolbar mToolbar;

    @Bind(R.id.courseList)
    public RecyclerView mCourseListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_overview);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mLayoutManager = new LinearLayoutManager(this);
        mCourseListView.setLayoutManager(mLayoutManager);

        mAdapter = new CourseListAdapter(new RecyclerViewAdapterBase.OnSelectionChangedListener<Course>() {
            @Override
            public void onSelectionChanged(final Course course) {
                editCourse(course);
            }
        });
        mCourseListView.setAdapter(mAdapter);

        List<Course> courses = new ArrayList<>();
        Course course1 = new Course();
        course1.setTitle("Course1");
        course1.setDescription("Description1");
        course1.setLocation("Verschoorstraat");
        course1.setPostalCode("5612SH");
        courses.add(course1);

        Course course2 = new Course();
        course2.setTitle("Course2");
        course2.setDescription("Description2");
        courses.add(course2);

        mAdapter.updateList(courses);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.course_overview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_enroll:
                startActivity(StudentOverviewActivity.getCallingIntent(this));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.fab)
    public void addCourse() {
        startActivity(CourseEditActivity.getCallingIntent(this));
    }

    private void editCourse(final Course course) {
        startActivity(CourseEditActivity.getCallingIntent(this, course));
    }
}
