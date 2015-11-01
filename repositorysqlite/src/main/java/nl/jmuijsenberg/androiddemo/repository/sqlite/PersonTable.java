package nl.jmuijsenberg.androiddemo.repository.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import com.squareup.sqlbrite.BriteDatabase;

import nl.jmuijsenberg.androiddemo.entities.Person;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;

public class PersonTable extends DatabaseTable<Person> {
    private static final String TABLE_NAME = "person";
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

    public PersonTable(BriteDatabase briteDatabase, RxSchedulers schedulers)
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
    protected String[] getDefaultWhereArgs(Person item) {
        return new String[]{Long.toString(item.getId())};
    }

    @Override
    protected ContentValues toContentValues(Person item) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRSTNAME, item.getFirstName());
        values.put(COLUMN_LASTNAME, item.getLastName());
        //values.put(COLUMN_DOB, person.getDateOfBirth().getTimeInMillis());
        //values.put(COLUMN_GENDER, person.getGender().getValue());
        return values;
    }

    @Override
    protected Person parseCursor(Cursor cursor) {
        Person person = new Person();
        person.setId(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)));
        person.setFirstName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIRSTNAME)));
        person.setLastName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LASTNAME)));
        //person.setDateOfBirth(DateTimeUtil.calendarFromMilliSeconds(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_DOB))));
        //person.setGender(Gender.getGender(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_GENDER))));
        return person;
    }
}
