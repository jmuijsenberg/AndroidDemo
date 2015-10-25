package nl.jmuijsenberg.androiddemo.repository.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import nl.jmuijsenberg.androiddemo.entities.Gender;
import nl.jmuijsenberg.androiddemo.entities.Person;
import nl.jmuijsenberg.androiddemo.util.java.datetime.DateTimeUtil;

public class Database {
    private static final String COMMA = ", ";

    public class Sql {
        private static final String DROP_IF_EXISTS = "DROP TABLE IF EXISTS ";
        private static final String CREATE_TABLE = "CREATE TABLE ";
    }

    public class ColumnType {
        private static final String PRIMARY_KEY = " INTEGER PRIMARY KEY AUTOINCREMENT";
        private static final String TEXT_NOT_NULL = " TEXT NOT NULL";
        private static final String TEXT_UNIQUE_NOT_NULL = " TEXT UNIQUE NOT NULL";
        private static final String TEXT = " TEXT";
        private static final String INTEGER = " INTEGER";
        private static final String LONG = " LONG";
        private static final String REAL = " REAL";
    }

    private Database() {
    }

    public static abstract class PersonTable {
        public static final String TABLE_NAME = "person";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_FIRSTNAME = "firstname";
        public static final String COLUMN_LASTNAME = "lastname";
        public static final String COLUMN_DOB = "dob";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_CREATED = "created";
        public static final String COLUMN_MODIFIED = "modified";

        public static final String CREATE = Sql.CREATE_TABLE + TABLE_NAME + "("
                + COLUMN_ID + ColumnType.PRIMARY_KEY + COMMA
                + COLUMN_FIRSTNAME + ColumnType.TEXT + COMMA
                + COLUMN_LASTNAME + ColumnType.TEXT + COMMA
                + COLUMN_GENDER + ColumnType.INTEGER + COMMA
                + COLUMN_DOB + ColumnType.LONG + COMMA
                + COLUMN_CREATED + ColumnType.LONG + COMMA
                + COLUMN_MODIFIED + ColumnType.LONG + ");";

        public static ContentValues toContentValues(Person person) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_FIRSTNAME, person.getFirstName());
            values.put(COLUMN_LASTNAME, person.getLastName());
            //values.put(COLUMN_DOB, person.getDateOfBirth().getTimeInMillis());
            //values.put(COLUMN_GENDER, person.getGender().getValue());
             return values;
        }

        public static Person parseCursor(Cursor cursor) {
            Person person = new Person();
            person.setId(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)));
            person.setFirstName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIRSTNAME)));
            person.setLastName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LASTNAME)));
            //person.setDateOfBirth(DateTimeUtil.calendarFromMilliSeconds(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_DOB))));
            //person.setGender(Gender.getGender(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_GENDER))));
            return person;
        }
    }
}
