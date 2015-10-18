package nl.jmuijsenberg.androiddemo.repository.sqlite;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import nl.jmuijsenberg.androiddemo.entities.Person;
import nl.jmuijsenberg.androiddemo.repository.Repository;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;
import rx.Observable;

public class RepositorySqlite implements Repository {
    private List<Person> persons = new ArrayList<>();
    private Context mContext;
    private RxSchedulers mSchedulers;

    public RepositorySqlite(Context context, RxSchedulers schedulers) {
        mContext = context;
        mSchedulers = schedulers;
    }

    @Override
    public Observable<List<Person>> getPersons() {
        return Observable.just(persons)
                .observeOn(mSchedulers.io());
    }

    @Override
    public Observable<Boolean> addPerson(Person person) {
        persons.add(person);
        return Observable.just(true)
                .observeOn(mSchedulers.io());
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
}
