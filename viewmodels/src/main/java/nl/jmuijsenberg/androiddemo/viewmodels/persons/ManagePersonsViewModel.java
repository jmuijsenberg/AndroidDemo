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
    private String mFirstName;
    private String mLastName;
    private Gender mGender;
    private Calendar mDateOfBirth;
    private List<Person> mPersonList;

    private List<Person> persons = new ArrayList<>();
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

    public void addPerson() {
        Person person = new Person();
        person.setFirstName(mFirstName);
        person.setLastName(mLastName);
        mController.addPerson(person)
                .observeOn(mSchedulers.main())
                .subscribe(new RxSubscriberBase<Boolean>() {
                    @Override
                    public void onNext(Boolean success) {

                    }
                });
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setGender(Gender gender) {
        this.mGender = gender;
    }

    public Gender getGender() {
        return mGender;
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
        this.mDateOfBirth = dateOfBirth;
    }

    public Calendar getDateOfBirth() {
        return mDateOfBirth;
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
