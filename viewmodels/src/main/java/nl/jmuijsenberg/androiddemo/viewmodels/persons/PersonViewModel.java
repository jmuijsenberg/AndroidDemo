package nl.jmuijsenberg.androiddemo.viewmodels.persons;

import java.util.Calendar;

import nl.jmuijsenberg.androiddemo.control.ManagePersonsController;
import nl.jmuijsenberg.androiddemo.entities.Gender;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;
import nl.jmuijsenberg.androiddemo.viewmodels.base.PropertyField;

public class PersonViewModel {
    private PropertyField<String> mFirstName;
    private PropertyField<String> mLastName;
    private PropertyField<Gender> mGender;
    private PropertyField<Calendar> mDateOfBirth;

    public PersonViewModel() {
        mFirstName = new PropertyField<>();
        mLastName = new PropertyField<>();
        mGender = new PropertyField<>();
        mDateOfBirth = new PropertyField<>();
    }

    public PropertyField<String> getFirstName()
    {
        return mFirstName;
    }

    public PropertyField<String> getLastName()
    {
        return mLastName;
    }

    public PropertyField<Gender> getGender()
    {
        return mGender;
    }

    public PropertyField<Calendar> getDateOfBirth()
    {
        return mDateOfBirth;
    }
}
