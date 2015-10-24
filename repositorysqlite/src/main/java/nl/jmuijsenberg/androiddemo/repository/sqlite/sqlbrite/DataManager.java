package nl.jmuijsenberg.androiddemo.repository.sqlite.sqlbrite;

import android.content.Context;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

public class DataManager {
    private DbOpenHelper mDbOpenHelper;
    private SqlBrite mSqlBrite;
    private BriteDatabase mBriteDatabase;

    public DataManager(Context context)
    {
        mDbOpenHelper = new DbOpenHelper(context);
        mSqlBrite = SqlBrite.create();
        mBriteDatabase = mSqlBrite.wrapDatabaseHelper(mDbOpenHelper);
    }
}
