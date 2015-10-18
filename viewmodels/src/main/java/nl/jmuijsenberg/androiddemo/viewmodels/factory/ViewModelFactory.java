package nl.jmuijsenberg.androiddemo.viewmodels.factory;

import nl.jmuijsenberg.androiddemo.control.ControllerFactory;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;
import nl.jmuijsenberg.androiddemo.viewmodels.persons.ManagePersonsViewModel;

public class ViewModelFactory {
    private ControllerFactory mControllerFactory;
    private RxSchedulers mSchedulers;

    public ViewModelFactory(ControllerFactory controllerFactory, RxSchedulers schedulers)
    {
        mControllerFactory = controllerFactory;
        mSchedulers = schedulers;
    }

    public ManagePersonsViewModel getManagePersonsViewModel() {
        return new ManagePersonsViewModel(mControllerFactory.getManagePersonsController(), mSchedulers);
    }
}
