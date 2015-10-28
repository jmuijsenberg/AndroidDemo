package nl.jmuijsenberg.androiddemo.viewmodels.persons;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import nl.jmuijsenberg.androiddemo.control.ManagePersonsController;
import nl.jmuijsenberg.androiddemo.entities.Gender;
import nl.jmuijsenberg.androiddemo.entities.Person;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSubscriberBase;
import rx.Observable;
import rx.Subscriber;

public class ManagePersonsViewModel {
    private List<Person> mPersonList;
    private Person mSelectedPerson = new Person();
    private final ManagePersonsController mController;
    private PersonDetailListener mPersonDetailListener;
    private PersonListListener mPersonListListener;
    private final RxSchedulers mSchedulers;

    public ManagePersonsViewModel(ManagePersonsController controller, RxSchedulers schedulers) {
        mController = controller;
        mSchedulers = schedulers;
        mPersonList = new ArrayList<>();
    }

    public void attachDetailView(PersonDetailListener personDetailListener)
    {
        mPersonDetailListener = personDetailListener;
    }

    public void detachDetailView()
    {
        mPersonDetailListener = null;
    }

    public void attachListView(PersonListListener personListListener)
    {
        mPersonListListener = personListListener;

        mController.getPersons()
                .observeOn(mSchedulers.main())
                .subscribe(new Subscriber<List<Person>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        mPersonListListener.onException(e);
                    }

                    @Override
                    public void onNext(List<Person> persons) {
                        if (mPersonListListener != null) {
                            mPersonListListener.onListChanged(persons);
                        }
                    }
                });
    }

    public void detachListView()
    {
        mPersonListListener = null;
    }

    public void setSelectedPerson(Person person)
    {
        mSelectedPerson = new Person(person);
        if (mPersonDetailListener != null) {
            mPersonDetailListener.onFirstNameChanged(mSelectedPerson.getFirstName());
            mPersonDetailListener.onLastNameChanged(mSelectedPerson.getLastName());
        }
    }

    public void newPerson() {
        setSelectedPerson(new Person());
    }

    public Person getSelectedPerson() {
        return mSelectedPerson;
    }

    public void savePerson() {
        Observable<Boolean> action = (mSelectedPerson.getId() == 0) ? mController.addPerson(mSelectedPerson) : mController.updatePerson(mSelectedPerson);

        action.observeOn(mSchedulers.main())
                .subscribe(new RxSubscriberBase<Boolean>() {
                    @Override
                    public void onNext(Boolean success) {

                    }
                });
    }

    public void deletePerson() {
        if (mSelectedPerson.getId() != 0) {
            Observable<Boolean> action = mController.deletePerson(mSelectedPerson);

            action.observeOn(mSchedulers.main())
                    .subscribe(new RxSubscriberBase<Boolean>() {
                        @Override
                        public void onNext(Boolean success) {
                            if (success)
                            {
                                newPerson();
                            }
                        }
                    });
        }
    }

    public interface PersonDetailListener {
        public void onFirstNameChanged(String firstName);

        public void onLastNameChanged(String lastName);

        public void onGenderChanged(String gender);

        public void onDateOfBirthChanged(String dateOfBirth);

        public void onException(Throwable e);
    }

    public interface PersonListListener {
        public void onListChanged(List<Person> persons);
        public void onException(Throwable e);
    }
}
