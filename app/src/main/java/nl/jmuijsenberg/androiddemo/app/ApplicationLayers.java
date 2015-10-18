package nl.jmuijsenberg.androiddemo.app;

import android.content.Context;

import nl.jmuijsenberg.androiddemo.control.ControllerFactory;
import nl.jmuijsenberg.androiddemo.devices.DeviceFactory;
import nl.jmuijsenberg.androiddemo.repository.Repository;
import nl.jmuijsenberg.androiddemo.repository.sqlite.RepositorySqlite;
import nl.jmuijsenberg.androiddemo.util.android.rxjava.RxAndroidSchedulers;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;
import nl.jmuijsenberg.androiddemo.viewmodels.ViewModelFactory;

public class ApplicationLayers {
    private Context mContext;
    private ViewModelFactory mMainViewModel;
    private RxSchedulers mSchedulers;
    private Repository mRepository;
    private DeviceFactory mDeviceFactory;
    private ViewModelFactory mViewModelFactory;
    private ControllerFactory mControllerFactory;

    public ApplicationLayers(Context context) {
        mContext = context;
        mSchedulers = new RxAndroidSchedulers();
        mRepository = new RepositorySqlite(context, mSchedulers);
        mDeviceFactory = new DeviceFactory();
        mControllerFactory = new ControllerFactory(mDeviceFactory, mRepository, mSchedulers);
        mViewModelFactory = new ViewModelFactory(mControllerFactory, mSchedulers);
    }

    public void cleanup() {

    }

    public ViewModelFactory getViewModelFactory()
    {
        return mViewModelFactory;
    }
}
