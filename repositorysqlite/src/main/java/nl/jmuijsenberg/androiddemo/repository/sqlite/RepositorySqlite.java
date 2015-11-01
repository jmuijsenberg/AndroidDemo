package nl.jmuijsenberg.androiddemo.repository.sqlite;

import android.content.Context;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.ArrayList;
import java.util.List;

import nl.jmuijsenberg.androiddemo.entities.Person;
import nl.jmuijsenberg.androiddemo.repository.Repository;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;
import rx.Observable;

public class RepositorySqlite implements Repository {
    private DatabaseOpenHelper mDbOpenHelper;
    private SqlBrite mSqlBrite;
    private BriteDatabase mBriteDatabase;
    private RxSchedulers mSchedulers;

    private PersonTable mPersonTable;

    public RepositorySqlite(Context context, RxSchedulers schedulers) {
        mSchedulers = schedulers;

        mDbOpenHelper = new DatabaseOpenHelper(context);
        mSqlBrite = SqlBrite.create();
        mBriteDatabase = mSqlBrite.wrapDatabaseHelper(mDbOpenHelper);

        mPersonTable = new PersonTable(mBriteDatabase, mSchedulers);
    }

    @Override
    public Observable<List<Person>> getPersons() {
        return mPersonTable.getItems();
    }

    @Override
    public Observable<Boolean> addPerson(final Person person) {
        return mPersonTable.insert(person);
    }

    @Override
    public Observable<Boolean> updatePerson(final Person person) {
        return mPersonTable.update(person);
    }

    @Override
    public Observable<Boolean> deletePerson(final Person person) {
        return mPersonTable.delete(person);
    }
}
