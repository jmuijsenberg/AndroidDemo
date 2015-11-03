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
import nl.jmuijsenberg.androiddemo.app.ApplicationExtension;
import nl.jmuijsenberg.androiddemo.app.adapters.CourseListAdapter;
import nl.jmuijsenberg.androiddemo.app.adapters.RecyclerViewAdapterBase;
import nl.jmuijsenberg.androiddemo.entities.Course;
import nl.jmuijsenberg.androiddemo.viewmodels.courses.CourseEditViewModel;
import nl.jmuijsenberg.androiddemo.viewmodels.courses.CourseOverviewViewModel;
import nl.jmuijsenberg.androiddemo.viewmodels.factory.ViewModelFactory;

public class CourseOverviewActivity extends AppCompatActivity implements CourseOverviewViewModel.CourseOverviewListener {
    private CourseListAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private CourseOverviewViewModel mViewModel;

    @Bind(R.id.fab)
    public FloatingActionButton mFloatingActionButton;
    @Bind(R.id.toolbar)
    public Toolbar mToolbar;
    @Bind(R.id.courseList)
    public RecyclerView mCourseRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_overview);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mLayoutManager = new LinearLayoutManager(this);
        mCourseRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CourseListAdapter(new RecyclerViewAdapterBase.OnSelectionChangedListener<Course>() {
            @Override
            public void onSelectionChanged(final Course course) {
                editCourse(course);
            }
        });

        mCourseRecyclerView.setAdapter(mAdapter);

        ViewModelFactory viewModelFactory = ((ApplicationExtension) getApplicationContext()).getViewModelFactory();
        mViewModel = viewModelFactory.getCourseOverviewViewModel();
        mViewModel.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.detachView();
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

    @Override
    public void onListChanged(List<Course> courses) {
        mAdapter.updateList(courses);
    }

    @Override
    public void onException(Throwable e) {

    }
}
