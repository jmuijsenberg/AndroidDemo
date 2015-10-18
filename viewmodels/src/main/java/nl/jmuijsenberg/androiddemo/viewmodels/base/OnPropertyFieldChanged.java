package nl.jmuijsenberg.androiddemo.viewmodels.base;

public abstract class OnPropertyFieldChanged<T> {
    public abstract void onPropertyChanged(T value);
}
