package nl.jmuijsenberg.androiddemo.viewmodels.base;

import java.util.ArrayList;
import java.util.List;

public class PropertyField<T> {
    private T mValue;
    private List<OnPropertyFieldChanged> observers;
    OnPropertyValidate mOnValidate;

    public PropertyField()
    {
        observers = new ArrayList<>();
    }

    public PropertyField(T value)
    {
        mValue = value;
        observers = new ArrayList<>();
    }

    public PropertyField(OnPropertyValidate onValidate)
    {
        mOnValidate = onValidate;
        observers = new ArrayList<>();
    }

    public PropertyField(T value, OnPropertyValidate onValidate)
    {
        mValue = value;
        mOnValidate = onValidate;
        observers = new ArrayList<>();
    }

    public void setui(T value)
    {
        if ((mValue == null) || (!mValue.equals(value))) {
            mValue = value;

            if (mOnValidate != null) {
                mOnValidate.validate();
            }
        }
    }

    public void set(T value)
    {
        if ((mValue == null) ||
            (!mValue.equals(value))) {
            mValue = value;

            if (mOnValidate != null) {
                mOnValidate.validate();
            }

            for (OnPropertyFieldChanged observer : observers) {
                observer.onPropertyChanged(value);
            }
        }
    }

    public T get()
    {
        return mValue;
    }

    public void addObserver(OnPropertyFieldChanged observer)
    {
        observers.add(observer);
    }

    public void removeObserver(OnPropertyFieldChanged observer)
    {
        observers.remove(observer);
    }
}


