package nl.jmuijsenberg.androiddemo.viewmodels.persons;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import nl.jmuijsenberg.androiddemo.control.ManagePersonsController;
import nl.jmuijsenberg.androiddemo.entities.Gender;
import nl.jmuijsenberg.androiddemo.entities.Person;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSubscriberBase;
import nl.jmuijsenberg.androiddemo.viewmodels.base.Command;
import nl.jmuijsenberg.androiddemo.viewmodels.base.PropertyField;
import rx.Subscriber;
import rx.functions.Func1;

public class ManagePersonsViewModel {
    private ManagePersonsController mController;
    private RxSchedulers mSchedulers;
    private PropertyField<PersonViewModel> mSelectedPerson;
    private PropertyField<List<PersonViewModel>> mPersonList;
    private PropertyField<String> mFirstName;
    private PropertyField<String> mLastName;
    private PropertyField<Gender> mGender;
    private PropertyField<Calendar> mDateOfBirth;
    private Command mAddPersonCommand;

    public ManagePersonsViewModel(ManagePersonsController controller, RxSchedulers schedulers) {
        mController = controller;
        mSchedulers = schedulers;
        mSelectedPerson = new PropertyField<>();
        mSelectedPerson.setInternal(new PersonViewModel());
        mPersonList = new PropertyField<>();
        mPersonList.setInternal(new ArrayList<PersonViewModel>());

        mAddPersonCommand = new AddCommand();

        mController.getPersons()
                .subscribeOn(mSchedulers.main())
                .map(new Func1<Person, PersonViewModel>() {
                    @Override
                    public PersonViewModel call(Person person) {
                        return new PersonViewModel(person);
                    }
                })
                .subscribe(new RxSubscriberBase<PersonViewModel>() {
                    @Override
                    public void onNext(PersonViewModel personViewModel) {
                        getPersonList().get().add(personViewModel);
                    }
                });
    }

    public PropertyField<PersonViewModel> getSelectedPerson() {
        return mSelectedPerson;
    }

    public PropertyField<List<PersonViewModel>> getPersonList() {
        return mPersonList;
    }

    public Command getAddPersonCommand() {
        return mAddPersonCommand;
    }

    private class AddCommand extends Command
    {
        @Override
        public void execute() {
            Person person = new Person();
            person.setFirstName(getSelectedPerson().get().getFirstName().get());
            person.setLastName(getSelectedPerson().get().getLastName().get());
            mController.addPerson(person)
                    .subscribeOn(mSchedulers.main())
                    .subscribe(new RxSubscriberBase<Person>() {
                        @Override
                        public void onNext(Person person) {

                        }
                    });
        }
    }
}
