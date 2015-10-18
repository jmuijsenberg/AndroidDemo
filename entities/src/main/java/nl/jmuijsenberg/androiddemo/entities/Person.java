package nl.jmuijsenberg.androiddemo.entities;

import java.util.Calendar;

public class Person {
    private String mFirstName;
    private String mLastName;
    private Gender mGender;
    private Calendar mDateOfBirth;

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