package nl.jmuijsenberg.androiddemo.repository.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import nl.jmuijsenberg.androiddemo.entities.Gender;
import nl.jmuijsenberg.androiddemo.entities.Person;
import nl.jmuijsenberg.androiddemo.repository.Repository;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class RepositorySqlite implements Repository {
    private DatabaseOpenHelper mDbOpenHelper;
    private SqlBrite mSqlBrite;
    private BriteDatabase mBriteDatabase;
    private List<Person> persons = new ArrayList<>();
    private Context mContext;
    private RxSchedulers mSchedulers;

    public RepositorySqlite(Context context, RxSchedulers schedulers) {
        mContext = context;
        mSchedulers = schedulers;

        mDbOpenHelper = new DatabaseOpenHelper(context);
        mSqlBrite = SqlBrite.create();
        mBriteDatabase = mSqlBrite.wrapDatabaseHelper(mDbOpenHelper);
    }

    @Override
    public Observable<Person> getPersons() {
        return mBriteDatabase.createQuery(Database.PersonTable.TABLE_NAME, "SELECT * FROM " + Database.PersonTable.TABLE_NAME)
                .flatMap(new Func1<SqlBrite.Query, Observable<Cursor>>() {
                    @Override
                    public Observable<Cursor> call(SqlBrite.Query query) {
                        return fromCursor(query.run());
                    }
                }).map(new Func1<Cursor, Person>() {
                    @Override
                    public Person call(Cursor cursor) {
                        Log.d("SIZE", cursor.getCount() + "");
                        return Database.PersonTable.parseCursor(cursor);
                    }
                }).observeOn(mSchedulers.io());
    }

    @Override
    public Observable<Person> addPerson(final Person person) {
        return Observable.create(new Observable.OnSubscribe<Person>() {
            @Override
            public void call(Subscriber<? super Person> subscriber) {
                long result = mBriteDatabase.insert(Database.PersonTable.TABLE_NAME, Database.PersonTable.toContentValues(person));
                if (result >= 0) subscriber.onNext(person);
                subscriber.onCompleted();
            }
        }).observeOn(mSchedulers.io());
    }



    @Override
    public Observable<Boolean> deletePeson(Person person) {
        persons.remove(person);
        return Observable.just(true)
                .observeOn(mSchedulers.io());
    }

    @Override
    public Observable<Boolean> updatePerson(Person person) {
        return Observable.just(false)
                .observeOn(mSchedulers.io());
    }

    // Copied from obsoleterx.android.content.ContentObservable
    public static Observable<Cursor> fromCursor(final Cursor cursor) {
        return Observable.create(new OnSubscribeCursor(cursor));
    }
}
