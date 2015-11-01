package nl.jmuijsenberg.androiddemo.repository.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import com.squareup.sqlbrite.BriteDatabase;

import nl.jmuijsenberg.androiddemo.entities.Enrollment;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;

public class EnrollmentTable  extends DatabaseTable<Enrollment>{
    private static final String TABLE_NAME = "enrollments";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_COURSE = "course";
    private static final String COLUMN_STUDENT = "student";
    private static final String COLUMN_REGISTRATION_DATE = "registration";
    private static final String COLUMN_CANCELATION_DATE = "cancelation";

    public static final String CREATE_TABLE = SqlKeyword.CREATE_TABLE + TABLE_NAME + "("
            + COLUMN_ID + SqlColumnType.PRIMARY_KEY + SqlKeyword.COMMA
            + COLUMN_COURSE + SqlColumnType.LONG + SqlKeyword.COMMA
            + COLUMN_STUDENT + SqlColumnType.LONG + SqlKeyword.COMMA
            + COLUMN_REGISTRATION_DATE + SqlColumnType.LONG + SqlKeyword.COMMA
            + COLUMN_CANCELATION_DATE + SqlColumnType.LONG + ");";

    public static final String DROP_TABLE = SqlKeyword.DROP_IF_EXISTS + TABLE_NAME;

    public EnrollmentTable(BriteDatabase briteDatabase, RxSchedulers schedulers)
    {
        super(briteDatabase, schedulers);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected String getDefaultWhereClause() {
        return COLUMN_ID + "= ? ";
    }

    @Override
    protected String[] getDefaultWhereArgs(Enrollment enrollment) {
        return new String[]{Long.toString(enrollment.getId())};
    }

    @Override
    protected ContentValues toContentValues(Enrollment enrollment) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, enrollment.getId());
        values.put(COLUMN_COURSE, enrollment.getCourseId());
        values.put(COLUMN_STUDENT, enrollment.getStudentId());
        values.put(COLUMN_REGISTRATION_DATE, enrollment.getRegistrationDate());
        values.put(COLUMN_CANCELATION_DATE, enrollment.getCancelationDate());

        return values;
    }

    @Override
    protected Enrollment parseCursor(Cursor cursor) {
        Enrollment enrollment = new Enrollment();
        enrollment.setId(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)));
        enrollment.setCourse(getLong(cursor, COLUMN_COURSE));
        enrollment.setStudent(getLong(cursor, COLUMN_STUDENT));
        enrollment.setRegistrationDate(getNullableLong(cursor, COLUMN_REGISTRATION_DATE));
        enrollment.setCancelationDate(getNullableLong(cursor, COLUMN_CANCELATION_DATE));
        return enrollment;
    }
}
