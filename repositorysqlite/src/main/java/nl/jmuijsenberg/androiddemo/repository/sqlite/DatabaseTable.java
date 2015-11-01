package nl.jmuijsenberg.androiddemo.repository.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.ArrayList;
import java.util.List;

import nl.jmuijsenberg.androiddemo.entities.Gender;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public abstract class DatabaseTable<T> {
    private BriteDatabase mBriteDatabase;
    private RxSchedulers mSchedulers;

    public DatabaseTable(BriteDatabase briteDatabase, RxSchedulers schedulers)
    {
        mBriteDatabase = briteDatabase;
        mSchedulers = schedulers;
    }

    public Observable<List<T>> getItems() {
        return mBriteDatabase.createQuery(getTableName(), SqlKeyword.SELECT_FROM + getTableName())
                .map(new Func1<SqlBrite.Query, List<T>>() {
                    @Override
                    public List<T> call(SqlBrite.Query query) {
                        Cursor cursor = query.run();
                        List<T> items = new ArrayList<>();
                        while (cursor.moveToNext()) {
                            items.add(parseCursor(cursor));
                        }
                        return items;
                    }
                }).subscribeOn(mSchedulers.io());
    }

    public Observable<Boolean> insert(final T item) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                long result = mBriteDatabase.insert(getTableName(), toContentValues(item));
                subscriber.onNext(result >= 0);
                subscriber.onCompleted();
            }
        }).subscribeOn(mSchedulers.io());
    }

    public Observable<Boolean> update(final T item) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                long result = mBriteDatabase.update(getTableName(), toContentValues(item), getDefaultWhereClause(), getDefaultWhereArgs(item));
                subscriber.onNext(result >= 0);
                subscriber.onCompleted();
            }
        }).subscribeOn(mSchedulers.io());
    }

    public Observable<Boolean> delete(final T item) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                long result = mBriteDatabase.delete(getTableName(), getDefaultWhereClause(), getDefaultWhereArgs(item));
                subscriber.onNext(result >= 0);
                subscriber.onCompleted();
            }
        }).subscribeOn(mSchedulers.io());
    }

    protected Long getLong(Cursor cursor, String columnName)
    {
        int columnIndex = cursor.getColumnIndexOrThrow(columnName);
        return cursor.getLong(columnIndex);
    }

    protected Long getNullableLong(Cursor cursor, String columnName)
    {
        int columnIndex = cursor.getColumnIndexOrThrow(columnName);
        return (!cursor.isNull(columnIndex)) ? cursor.getLong(columnIndex) : null;
    }

    protected int getInt(Cursor cursor, String columnName)
    {
        int columnIndex = cursor.getColumnIndexOrThrow(columnName);
        return cursor.getInt(columnIndex);
    }

    protected Integer getNullableInt(Cursor cursor, String columnName)
    {
        int columnIndex = cursor.getColumnIndexOrThrow(columnName);
        return (!cursor.isNull(columnIndex)) ? cursor.getInt(columnIndex) : null;
    }

    protected Gender getNullableGender(Cursor cursor, String columnName)
    {
        int columnIndex = cursor.getColumnIndexOrThrow(columnName);
        return (!cursor.isNull(columnIndex)) ? Gender.getGender(cursor.getInt(columnIndex)) : null;
    }

    protected String getString(Cursor cursor, String columnName)
    {
        int columnIndex = cursor.getColumnIndexOrThrow(columnName);
        return cursor.getString(columnIndex);
    }

    protected abstract String getTableName();
    protected abstract String getDefaultWhereClause();
    protected abstract String[] getDefaultWhereArgs(T item);
    protected abstract ContentValues toContentValues(T item);
    protected abstract T parseCursor(Cursor cursor);


}
