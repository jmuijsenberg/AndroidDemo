package nl.jmuijsenberg.androiddemo.app.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nl.jmuijsenberg.androiddemo.R;
import nl.jmuijsenberg.androiddemo.app.ApplicationExtension;
import nl.jmuijsenberg.androiddemo.app.dialogs.ExceptionDialogFragment;
import nl.jmuijsenberg.androiddemo.entities.Course;
import nl.jmuijsenberg.androiddemo.viewmodels.courses.CourseEditViewModel;
import nl.jmuijsenberg.androiddemo.viewmodels.factory.ViewModelFactory;

public class CourseEditActivity extends AppCompatActivity implements CourseEditViewModel.CourseEditListener {
    private static String INTENT_COURSE_PARAMETER = "Course";
    private CourseEditViewModel mViewModel;

    @Bind(R.id.fab)
    public FloatingActionButton mFloatingActionButton;
    @Bind(R.id.toolbar)
    public Toolbar mToolbar;
    @Bind(R.id.courseTitleValue)
    EditText mTitleEditText;
    @Bind(R.id.courseDescriptionValue)
    EditText mDescriptionEditText;
    @Bind(R.id.courseLocationValue)
    TextView mLocationEditText;
    @Bind(R.id.coursePostalCodeValue)
    TextView mPostalCodeEditText;
    @Bind(R.id.courseSaveButton)
    Button mSaveButton;
    @Bind(R.id.courseCancelSaveButton)
    Button mCancelButton;

    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, CourseEditActivity.class);
        return intent;
    }

    public static Intent getCallingIntent(Context context, Course course) {
        Intent intent = new Intent(context, CourseEditActivity.class);
        intent.putExtra(INTENT_COURSE_PARAMETER, (Serializable) course);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_edit);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        ViewModelFactory viewModelFactory = ((ApplicationExtension) getApplicationContext()).getViewModelFactory();
        mViewModel = viewModelFactory.getCourseEditViewModel();
        mViewModel.attachView(this);

        Bundle bundle = getIntent().getExtras();
        if ((bundle != null) && (bundle.getSerializable(INTENT_COURSE_PARAMETER) != null)) {
            Course course = (Course) bundle.getSerializable(INTENT_COURSE_PARAMETER);
            mViewModel.setSelectedCourse(course);
        }
        else
        {
            mViewModel.newCourse();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.detachView();
    }

    @OnClick(R.id.courseSaveButton)
    public void onSave(Button button) {
        mViewModel.saveCourse();
    }

    @OnClick(R.id.courseCancelSaveButton)
    public void onCancel(Button button) {

    }

    @Override
    public void onTitleChanged(String title) {
        mTitleEditText.setText(title);
    }

    @Override
    public void onDescriptionChanged(String description) {
        mDescriptionEditText.setText(description);
    }

    @Override
    public void onException(Throwable e) {

    }
}
