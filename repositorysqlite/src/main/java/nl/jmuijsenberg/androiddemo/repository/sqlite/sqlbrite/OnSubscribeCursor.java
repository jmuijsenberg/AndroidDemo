package nl.jmuijsenberg.androiddemo.repository.sqlite.sqlbrite;

import android.database.Cursor;

import rx.Observable;
import rx.Subscriber;

// Copied from obsolete rx.android.content.OnSubscribeCursor
final class OnSubscribeCursor implements Observable.OnSubscribe<Cursor> {

    private final Cursor cursor;

    OnSubscribeCursor(final Cursor cursor) {
        this.cursor = cursor;
    }

    @Override
    public void call(final Subscriber<? super Cursor> subscriber) {
        try {
            while (!subscriber.isUnsubscribed() && cursor.moveToNext()) {
                subscriber.onNext(cursor);
            }
        } finally {
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }
        subscriber.onCompleted();
    }

}
