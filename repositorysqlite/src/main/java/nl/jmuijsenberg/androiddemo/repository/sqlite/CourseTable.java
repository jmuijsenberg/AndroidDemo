package nl.jmuijsenberg.androiddemo.repository.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import com.squareup.sqlbrite.BriteDatabase;

import nl.jmuijsenberg.androiddemo.entities.Course;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;

public class CourseTable  extends DatabaseTable<Course> {
    private static final String TABLE_NAME = "courses";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_CODE = "code";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_POSTALCODE = "postalcode";
    private static final String COLUMN_HOUSENUMBER = "housenumber";

    public static final String CREATE_TABLE = SqlKeyword.CREATE_TABLE + TABLE_NAME + "("
            + COLUMN_ID + SqlColumnType.PRIMARY_KEY + SqlKeyword.COMMA
            + COLUMN_CODE + SqlColumnType.TEXT + SqlKeyword.COMMA
            + COLUMN_TITLE + SqlColumnType.TEXT + SqlKeyword.COMMA
            + COLUMN_DESCRIPTION + SqlColumnType.TEXT + SqlKeyword.COMMA
            + COLUMN_DATE + SqlColumnType.LONG + SqlKeyword.COMMA
            + COLUMN_LOCATION + SqlColumnType.TEXT + SqlKeyword.COMMA
            + COLUMN_POSTALCODE + SqlColumnType.TEXT + SqlKeyword.COMMA
            + COLUMN_HOUSENUMBER + SqlColumnType.TEXT + ");";

    public static final String DROP_TABLE = SqlKeyword.DROP_IF_EXISTS + TABLE_NAME;

    public CourseTable(BriteDatabase briteDatabase, RxSchedulers schedulers)
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
    protected String[] getDefaultWhereArgs(Course item) {
        return new String[]{Long.toString(item.getId())};
    }

    @Override
    protected ContentValues toContentValues(Course course) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID , course.getId());
        values.put(COLUMN_CODE , course.getCode());
        values.put(COLUMN_TITLE , course.getTitle());
        values.put(COLUMN_DESCRIPTION , course.getDescription());
        values.put(COLUMN_DATE , course.getDate());
        values.put(COLUMN_LOCATION , course.getLocation());
        values.put(COLUMN_POSTALCODE , course.getPostalCode());
        values.put(COLUMN_HOUSENUMBER , course.getHousenumber());
        return values;
    }

    @Override
    protected Course parseCursor(Cursor cursor) {
        Course course = new Course();
        course.setId(getLong(cursor, COLUMN_ID));
        course.setCode(getString(cursor, COLUMN_CODE));
        course.setTitle(getString(cursor, COLUMN_TITLE));
        course.setDescription(getString(cursor, COLUMN_DESCRIPTION));
        course.setDate(getNullableLong(cursor, COLUMN_DATE));
        course.setLocation(getString(cursor, COLUMN_LOCATION));
        course.setPostalCode(getString(cursor, COLUMN_POSTALCODE));
        course.setHousenumber(getString(cursor, COLUMN_HOUSENUMBER));
        return course;
    }
}
