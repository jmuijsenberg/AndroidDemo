package nl.jmuijsenberg.androiddemo.app;

import android.content.Context;

import nl.jmuijsenberg.androiddemo.control.ControllerFactory;
import nl.jmuijsenberg.androiddemo.devices.DeviceFactory;
import nl.jmuijsenberg.androiddemo.repository.Repository;
import nl.jmuijsenberg.androiddemo.repository.sqlite.RepositorySqlite;
import nl.jmuijsenberg.androiddemo.util.android.logging.AndroidLogger;
import nl.jmuijsenberg.androiddemo.util.android.rxjava.RxAndroidSchedulers;
import nl.jmuijsenberg.androiddemo.util.java.logging.Logger;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;
import nl.jmuijsenberg.androiddemo.viewmodels.factory.ViewModelFactory;

public class ApplicationLayers {
    private RxSchedulers mSchedulers;
    private Repository mRepository;
    private DeviceFactory mDeviceFactory;
    private ViewModelFactory mViewModelFactory;
    private ControllerFactory mControllerFactory;
    private AndroidLogger mLogcatLog;

    public ApplicationLayers(Context context) {
        mLogcatLog = new AndroidLogger();
        Logger.attachListener(mLogcatLog);
        mSchedulers = new RxAndroidSchedulers();
        mRepository = new RepositorySqlite(context, mSchedulers);
        mDeviceFactory = new DeviceFactory();
        mControllerFactory = new ControllerFactory(mDeviceFactory, mRepository, mSchedulers);
        mViewModelFactory = new ViewModelFactory(mControllerFactory, mSchedulers);
    }

    public void cleanup() {
        Logger.detachListener(mLogcatLog);
    }

    public ViewModelFactory getViewModelFactory()
    {
        return mViewModelFactory;
    }
}
