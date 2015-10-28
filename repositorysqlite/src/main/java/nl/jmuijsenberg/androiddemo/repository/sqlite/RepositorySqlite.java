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
    public Observable<List<Person>> getPersons() {
        return mBriteDatabase.createQuery(Database.PersonTable.TABLE_NAME, Database.Sql.SELECT_FROM + Database.PersonTable.TABLE_NAME)
                .map(new Func1<SqlBrite.Query, List<Person>>() {
                    @Override
                    public List<Person> call(SqlBrite.Query query) {
                        Cursor cursor = query.run();
                        List<Person> persons = new ArrayList<>();
                        while (cursor.moveToNext()) {
                            persons.add(Database.PersonTable.parseCursor(cursor));
                        }
                        return persons;
                    }
                }).subscribeOn(mSchedulers.io());
    }

    @Override
    public Observable<Boolean> addPerson(final Person person) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                long result = mBriteDatabase.insert(Database.PersonTable.TABLE_NAME, Database.PersonTable.toContentValues(person));
                subscriber.onNext(result >= 0);
                subscriber.onCompleted();
            }
        }).subscribeOn(mSchedulers.io());
    }

    @Override
    public Observable<Boolean> updatePerson(final Person person) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                String whereClause = Database.PersonTable.COLUMN_ID + "= ? ";
                String[] whereArgs = new String[]{Long.toString(person.getId())};
                long result = mBriteDatabase.update(Database.PersonTable.TABLE_NAME, Database.PersonTable.toContentValues(person), whereClause, whereArgs);
                subscriber.onNext(result >= 0);
                subscriber.onCompleted();
            }
        }).subscribeOn(mSchedulers.io());
    }

    @Override
    public Observable<Boolean> deletePerson(final Person person) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                String whereClause = Database.PersonTable.COLUMN_ID + "= ? ";
                String[] whereArgs = new String[]{Long.toString(person.getId())};
                long result = mBriteDatabase.delete(Database.PersonTable.TABLE_NAME, whereClause, whereArgs);
                subscriber.onNext(result >= 0);
                subscriber.onCompleted();
            }
        }).subscribeOn(mSchedulers.io());
    }
}
