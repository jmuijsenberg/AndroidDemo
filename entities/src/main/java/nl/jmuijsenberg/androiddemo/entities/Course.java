package nl.jmuijsenberg.androiddemo.entities;

import java.io.Serializable;

public class Course implements Serializable {
    private long mId;
    private String mCode;
    private String mTitle;
    private String mDescription;
    private Long mDate;
    private String mLocation;
    private String mPostalCode;
    private String mHousenumber;

    public Course() {
    }

    public Course(Course course) {
        mId = course.mId;
        mCode = course.mCode;
        mTitle = course.mTitle;
        mDescription = course.mDescription;
        mDate = course.mDate;
        mLocation = course.mLocation;
        mPostalCode = course.mPostalCode;
        mHousenumber = course.mHousenumber;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Long getDate() {
        return mDate;
    }

    public void setDate(Long date) {
        mDate = date;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public String getPostalCode() {
        return mPostalCode;
    }

    public void setPostalCode(String postalCode) {
        mPostalCode = postalCode;
    }

    public String getHousenumber() {
        return mHousenumber;
    }

    public void setHousenumber(String housenumber) {
        mHousenumber = housenumber;
    }
}
