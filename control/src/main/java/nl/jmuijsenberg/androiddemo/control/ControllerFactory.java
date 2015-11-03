package nl.jmuijsenberg.androiddemo.control;

import nl.jmuijsenberg.androiddemo.devices.DeviceFactory;
import nl.jmuijsenberg.androiddemo.repository.Repository;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;

public class ControllerFactory {
    private DeviceFactory mDevice;
    private Repository mRepository;
    private RxSchedulers mSchedulers;

    public ControllerFactory(DeviceFactory device, Repository repository, RxSchedulers schedulers)
    {
        mDevice = device;
        mRepository = repository;
        mSchedulers = schedulers;
    }

    public ManageCoursesController getManageCoursesController() {
        return new ManageCoursesController(mRepository, mSchedulers);
    }

    public ManageStudentsController getManageStudentsController() {
        return new ManageStudentsController(mRepository, mSchedulers);
    }
}
