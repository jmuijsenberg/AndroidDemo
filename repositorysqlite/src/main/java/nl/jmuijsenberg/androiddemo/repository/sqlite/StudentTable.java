package nl.jmuijsenberg.androiddemo.repository.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import com.squareup.sqlbrite.BriteDatabase;

import nl.jmuijsenberg.androiddemo.entities.Gender;
import nl.jmuijsenberg.androiddemo.entities.Student;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;

public class StudentTable  extends DatabaseTable<Student> {
    private static final String TABLE_NAME = "students";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_FIRSTNAME = "firstname";
    private static final String COLUMN_LASTNAME = "lastname";
    private static final String COLUMN_DOB = "dob";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_CREATED = "created";
    private static final String COLUMN_MODIFIED = "modified";

    public static final String CREATE_TABLE = SqlKeyword.CREATE_TABLE + TABLE_NAME + "("
            + COLUMN_ID + SqlColumnType.PRIMARY_KEY + SqlKeyword.COMMA
            + COLUMN_FIRSTNAME + SqlColumnType.TEXT + SqlKeyword.COMMA
            + COLUMN_LASTNAME + SqlColumnType.TEXT + SqlKeyword.COMMA
            + COLUMN_GENDER + SqlColumnType.INTEGER + SqlKeyword.COMMA
            + COLUMN_DOB + SqlColumnType.LONG + SqlKeyword.COMMA
            + COLUMN_CREATED + SqlColumnType.LONG + SqlKeyword.COMMA
            + COLUMN_MODIFIED + SqlColumnType.LONG + ");";

    public static final String DROP_TABLE = SqlKeyword.DROP_IF_EXISTS + TABLE_NAME;

    public StudentTable(BriteDatabase briteDatabase, RxSchedulers schedulers)
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
    protected String[] getDefaultWhereArgs(Student item) {
        return new String[]{Long.toString(item.getId())};
    }

    @Override
    protected ContentValues toContentValues(Student student) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRSTNAME, student.getFirstName());
        values.put(COLUMN_LASTNAME, student.getLastName());
        values.put(COLUMN_DOB, student.getDateOfBirth());
        values.put(COLUMN_GENDER, student.getGender().getValue());
        return values;
    }

    @Override
    protected Student parseCursor(Cursor cursor) {
        Student student = new Student();
        student.setId(getLong(cursor, COLUMN_ID));
        student.setFirstName(getString(cursor, COLUMN_FIRSTNAME));
        student.setLastName(getString(cursor, COLUMN_LASTNAME));
        student.setDateOfBirth(getNullableLong(cursor, COLUMN_DOB));
        student.setGender(Gender.getGender(getNullableInt(cursor, COLUMN_GENDER)));
        return student;
    }
}
