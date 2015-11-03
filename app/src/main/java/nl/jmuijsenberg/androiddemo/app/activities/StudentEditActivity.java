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
import nl.jmuijsenberg.androiddemo.entities.Student;
import nl.jmuijsenberg.androiddemo.viewmodels.factory.ViewModelFactory;
import nl.jmuijsenberg.androiddemo.viewmodels.students.StudentEditViewModel;

public class StudentEditActivity extends AppCompatActivity implements StudentEditViewModel.StudentEditListener {
    private static String INTENT_STUDENT_PARAMETER = "Student";
    private StudentEditViewModel mViewModel;

    @Bind(R.id.fab)
    public FloatingActionButton mFloatingActionButton;
    @Bind(R.id.toolbar)
    public Toolbar mToolbar;
    @Bind(R.id.studentFirstNameValue)
    EditText mFirstNameText;
    @Bind(R.id.studentLastNameValue)
    EditText mLastNameText;
    @Bind(R.id.studentDateOfBirthValue)
    TextView mDateOfBirthText;
    @Bind(R.id.studentSaveButton)
    Button mSaveButton;
    @Bind(R.id.studentCancelSaveButton)
    Button mCancelButton;

    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, StudentEditActivity.class);
        return intent;
    }

    public static Intent getCallingIntent(Context context, Student student) {
        Intent intent = new Intent(context, StudentEditActivity.class);
        intent.putExtra(INTENT_STUDENT_PARAMETER, (Serializable) student);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_edit);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        ViewModelFactory viewModelFactory = ((ApplicationExtension) getApplicationContext()).getViewModelFactory();
        mViewModel = viewModelFactory.getStudentEditViewModel();
        mViewModel.attachView(this);

        Bundle bundle = getIntent().getExtras();
        if ((bundle != null) && (bundle.getSerializable(INTENT_STUDENT_PARAMETER) != null)) {
            Student student = (Student) bundle.getSerializable(INTENT_STUDENT_PARAMETER);
            mViewModel.setSelectedStudent(student);
        }
        else {
            mViewModel.newStudent();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.detachView();
    }

    @OnClick(R.id.studentSaveButton)
    public void onSave(Button button) {
        mViewModel.saveStudent();
    }

    @OnClick(R.id.studentCancelSaveButton)
    public void onCancel(Button button) {

    }

    @Override
    public void onFirstNameChanged(String firstName) {

    }

    @Override
    public void onLastNameChanged(String lastName) {

    }

    @Override
    public void onGenderChanged(String gender) {

    }

    @Override
    public void onDateOfBirthChanged(String dateOfBirth) {

    }

    @Override
    public void onException(Throwable e) {

    }
}
