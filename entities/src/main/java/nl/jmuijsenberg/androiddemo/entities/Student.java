package nl.jmuijsenberg.androiddemo.entities;

import java.io.Serializable;

public class Student implements Serializable {
    private long mId;
    private String mFirstName;
    private String mLastName;
    private Gender mGender;
    private Long mDateOfBirth;

    public Student() {
    }

    public Student(Student student) {
        mId = student.mId;
        mFirstName = student.mFirstName;
        mLastName = student.mLastName;
        mGender = student.mGender;
        mDateOfBirth = student.mDateOfBirth;
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

    public Long getDateOfBirth() {
        return mDateOfBirth;
    }

    public void setDateOfBirth(Long dateOfBirth) {
        mDateOfBirth = dateOfBirth;
    }
}
