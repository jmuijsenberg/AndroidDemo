package nl.jmuijsenberg.androiddemo.viewmodels;

import nl.jmuijsenberg.androiddemo.control.ControllerFactory;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;

public class ViewModelFactory {
    private ControllerFactory mControllerFactory;
    private RxSchedulers mSchedulers;

    public ViewModelFactory(ControllerFactory controllerFactory, RxSchedulers schedulers)
    {
        mControllerFactory = controllerFactory;
        mSchedulers = schedulers;
    }
}
