package nl.jmuijsenberg.androiddemo.control;

import java.util.List;

import nl.jmuijsenberg.androiddemo.entities.Person;
import nl.jmuijsenberg.androiddemo.repository.Repository;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;
import rx.Observable;

public class ManagePersonsController {
    private Repository mRepository;
    private RxSchedulers mSchedulers;

    public ManagePersonsController(Repository repository, RxSchedulers schedulers)
    {
        mRepository = repository;
        mSchedulers = schedulers;
    }

    public Observable<List<Person>> getPersons()
    {
        return mRepository.getPersons();
    }

    public Observable<Person> addPerson(Person person)
    {
        return mRepository.addPerson(person);
    }

    public Observable<Boolean> deletePeson(Person person)
    {
        return mRepository.deletePeson(person);
    }

    public Observable<Boolean> updatePerson(Person person)
    {
        return mRepository.updatePerson(person);
    }
}
