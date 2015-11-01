package nl.jmuijsenberg.androiddemo.repository.sqlite;

public class SqlColumnType {

    private SqlColumnType() {
    }

    public static final String PRIMARY_KEY = " INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final String TEXT_NOT_NULL = " TEXT NOT NULL";
    public static final String TEXT_UNIQUE_NOT_NULL = " TEXT UNIQUE NOT NULL";
    public static final String TEXT = " TEXT";
    public static final String INTEGER = " INTEGER";
    public static final String LONG = " LONG";
    public static final String REAL = " REAL";
}
