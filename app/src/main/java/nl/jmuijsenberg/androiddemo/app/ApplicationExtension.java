package nl.jmuijsenberg.androiddemo.app;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import nl.jmuijsenberg.androiddemo.BuildConfig;
import nl.jmuijsenberg.androiddemo.viewmodels.factory.ViewModelFactory;

public class ApplicationExtension extends Application {
    private RefWatcher refWatcher;
    private ApplicationLayers mApplicationLayers;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationLayers = new ApplicationLayers(getApplicationContext());

        if (BuildConfig.DEBUG) {
            enableStrictMode();
            initializeStetho();
            enableMemoryLeakDetection();
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mApplicationLayers != null)
        {
            mApplicationLayers.cleanup();
            mApplicationLayers = null;
        }
    }

    public ViewModelFactory getViewModelFactory()
    {
        return (mApplicationLayers != null) ? mApplicationLayers.getViewModelFactory() : null;
    }

    public static RefWatcher getRefWatcher(Context context) {
        ApplicationExtension application = (ApplicationExtension) context.getApplicationContext();
        return application.refWatcher;
    }

    private void enableStrictMode() {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .penaltyDialog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll()
                    .penaltyLog()
                    .build());
    }

    private void initializeStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }

    private void enableMemoryLeakDetection() {
        refWatcher = LeakCanary.install(this);
    }


}
