package nl.jmuijsenberg.androiddemo.repository.sqlite;

import android.content.Context;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.List;

import nl.jmuijsenberg.androiddemo.entities.Course;
import nl.jmuijsenberg.androiddemo.entities.Student;
import nl.jmuijsenberg.androiddemo.repository.Repository;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;
import rx.Observable;

public class RepositorySqlite implements Repository {
    private DatabaseOpenHelper mDbOpenHelper;
    private SqlBrite mSqlBrite;
    private BriteDatabase mBriteDatabase;
    private RxSchedulers mSchedulers;

    private StudentTable mStudentTable;
    private CourseTable mCourseTable;
    private EnrollmentTable mEnrollmentTable;

    public RepositorySqlite(Context context, RxSchedulers schedulers) {
        mSchedulers = schedulers;

        mDbOpenHelper = new DatabaseOpenHelper(context);
        mSqlBrite = SqlBrite.create();
        mBriteDatabase = mSqlBrite.wrapDatabaseHelper(mDbOpenHelper);

        mStudentTable = new StudentTable(mBriteDatabase, mSchedulers);
        mCourseTable = new CourseTable(mBriteDatabase, mSchedulers);
        mEnrollmentTable = new EnrollmentTable(mBriteDatabase, mSchedulers);
    }

    @Override
    public Observable<List<Student>> getStudents() {
        return mStudentTable.getItems();
    }

    @Override
    public Observable<Boolean> addStudent(final Student student) {
        return mStudentTable.insert(student);
    }

    @Override
    public Observable<Boolean> updateStudent(final Student student) {
        return mStudentTable.update(student);
    }

    @Override
    public Observable<Boolean> deleteStudent(final Student student) {
        return mStudentTable.delete(student);
    }

    @Override
    public Observable<List<Course>> getCourses() {
        return mCourseTable.getItems();
    }

    @Override
    public Observable<Boolean> addCourse(final Course course) {
        return mCourseTable.insert(course);
    }

    @Override
    public Observable<Boolean> updateCourse(final Course course) {
        return mCourseTable.update(course);
    }

    @Override
    public Observable<Boolean> deleteCourse(final Course course) {
        return mCourseTable.delete(course);
    }
}
