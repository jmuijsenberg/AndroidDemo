package nl.jmuijsenberg.androiddemo.app.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

import butterknife.Bind;
import butterknife.ButterKnife;
import nl.jmuijsenberg.androiddemo.R;
import nl.jmuijsenberg.androiddemo.entities.Course;
import nl.jmuijsenberg.androiddemo.entities.Student;

public class StudentEditActivity extends AppCompatActivity {
    private static String INTENT_STUDENT_PARAMETER = "Student";

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
    @Bind(R.id.saveButton)
    Button mSaveButton;
    @Bind(R.id.cancelButton)
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

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            if (bundle.getSerializable(INTENT_STUDENT_PARAMETER) != null) {
                Student student = (Student) bundle.getSerializable(INTENT_STUDENT_PARAMETER);
                mFirstNameText.setText(student.getFirstName());
                mLastNameText.setText(student.getLastName());
            }
        }
    }

}
