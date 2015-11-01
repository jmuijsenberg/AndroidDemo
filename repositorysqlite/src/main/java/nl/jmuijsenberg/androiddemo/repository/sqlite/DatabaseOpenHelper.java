package nl.jmuijsenberg.androiddemo.repository.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION = 2;
    private static final String DATABASE_NAME = "nl.jmuijsenberg.androiddemo.jvdm.db";

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null /* factory */, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL(PersonTable.CREATE_TABLE);
            db.execSQL(StudentTable.CREATE_TABLE);
            db.execSQL(CourseTable.CREATE_TABLE);
            db.execSQL(EnrollmentTable.CREATE_TABLE);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(PersonTable.DROP_TABLE);
        db.execSQL(StudentTable.DROP_TABLE);
        db.execSQL(CourseTable.DROP_TABLE);
        db.execSQL(EnrollmentTable.DROP_TABLE);
    }
}
