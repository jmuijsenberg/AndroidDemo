package nl.jmuijsenberg.androiddemo.repository;

import java.util.List;

import nl.jmuijsenberg.androiddemo.entities.Person;
import rx.Observable;

public interface Repository {
    Observable<List<Person>> getPersons();
    Observable<Boolean> addPerson(final Person person);
    Observable<Boolean> deletePeson(Person person);
    Observable<Boolean> updatePerson(Person person);
}
