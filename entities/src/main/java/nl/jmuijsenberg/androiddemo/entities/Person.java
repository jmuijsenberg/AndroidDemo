package nl.jmuijsenberg.androiddemo.entities;

import java.util.Calendar;

public class Person {
    private long mId;
    private String mFirstName;
    private String mLastName;
    private Gender mGender;
    private Calendar mDateOfBirth;

    public Person() {
    }

    public Person(Person person) {
        mId = person.mId;
        mFirstName = person.mFirstName;
        mLastName = person.mLastName;
        mGender = person.mGender;
        mDateOfBirth = person.mDateOfBirth;
    }

    public Person(String firstName,
                  String lastName,
                  Gender gender,
                  Calendar dateOfBirth) {
        mFirstName = firstName;
        mLastName = lastName;
        mGender = gender;
        mDateOfBirth = dateOfBirth;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public Gender getGender() {
        return mGender;
    }

    public void setGender(Gender gender) {
        mGender = gender;
    }

    public Calendar getDateOfBirth() {
        return mDateOfBirth;
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
        mDateOfBirth = dateOfBirth;
    }
}
