package nl.jmuijsenberg.androiddemo.control;

import java.util.List;

import nl.jmuijsenberg.androiddemo.entities.Person;
import nl.jmuijsenberg.androiddemo.repository.Repository;
import rx.Observable;

public class ManagePersonsController {
    private Repository mRepository;

    public ManagePersonsController(Repository repository)
    {
        mRepository = repository;
    }

    public Observable<List<Person>> getPersons()
    {
        return mRepository.getPersons();
    }

    public Observable<Boolean> addPerson(Person person)
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
