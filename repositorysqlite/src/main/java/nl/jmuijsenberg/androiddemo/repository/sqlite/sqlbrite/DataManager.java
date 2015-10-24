package nl.jmuijsenberg.androiddemo.repository.sqlite.sqlbrite;

import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.util.Log;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import nl.jmuijsenberg.androiddemo.entities.Person;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

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

    public Observable<Person> getPeople() {
        return mBriteDatabase.createQuery(Db.PersonTable.TABLE_NAME, "SELECT * FROM " + Db.PersonTable.TABLE_NAME)
                .flatMap(new Func1<SqlBrite.Query, Observable<Cursor>>() {
                    @Override
                    public Observable<Cursor> call(SqlBrite.Query query) {
                        return fromCursor(query.run());
                    }
                }).map(new Func1<Cursor, Person>() {
                    @Override
                    public Person call(Cursor cursor) {
                        Log.d("SIZE", cursor.getCount() + "");
                        return Db.PersonTable.parseCursor(cursor);
                    }
                });
    }

    public Observable<Person> savePerson(final Person person) {
        return Observable.create(new Observable.OnSubscribe<Person>() {
            @Override
            public void call(Subscriber<? super Person> subscriber) {
                long result = mBriteDatabase.insert(Db.PersonTable.TABLE_NAME, Db.PersonTable.toContentValues(person));
                if (result >= 0) subscriber.onNext(person);
                subscriber.onCompleted();
            }
        });
    }

    // Copied from obsoleterx.android.content.ContentObservable
    public static Observable<Cursor> fromCursor(final Cursor cursor) {
        return Observable.create(new OnSubscribeCursor(cursor));
    }
}
