package nl.jmuijsenberg.androiddemo.viewmodels.persons;

import nl.jmuijsenberg.androiddemo.control.ManagePersonsController;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;

public class ManagePersonsViewModel {
    private ManagePersonsController mController;
    private RxSchedulers mSchedulers;

    public ManagePersonsViewModel(ManagePersonsController controller, RxSchedulers schedulers) {
        mController = controller;
        mSchedulers = schedulers;
    }


}
