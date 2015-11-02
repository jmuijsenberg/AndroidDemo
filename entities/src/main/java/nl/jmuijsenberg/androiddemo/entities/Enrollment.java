package nl.jmuijsenberg.androiddemo.entities;

import java.io.Serializable;

public class Enrollment implements Serializable {
    private long mId;
    private long mStudentId;
    private long mCourseId;
    private Long mRegistrationDate;
    private Long mCancelationDate;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public long getStudentId() {
        return mStudentId;
    }

    public void setStudent(long studentId) {
        mStudentId = studentId;
    }

    public long getCourseId() {
        return mCourseId;
    }

    public void setCourse(long courseId) {
        mCourseId = courseId;
    }

    public Long getRegistrationDate() {
        return mRegistrationDate;
    }

    public void setRegistrationDate(Long registrationDate) {
        mRegistrationDate = registrationDate;
    }

    public Long getCancelationDate() {
        return mCancelationDate;
    }

    public void setCancelationDate(Long cancelationDate) {
        mCancelationDate = cancelationDate;
    }
}
