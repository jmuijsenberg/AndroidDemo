package nl.jmuijsenberg.androiddemo.app;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import nl.jmuijsenberg.androiddemo.BuildConfig;

public class ExtendedApplication extends Application {
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            enableStrictMode();
            initializeStetho();
            enableMemoryLeakDetection();
        }
    }

    public static RefWatcher getRefWatcher(Context context) {
        ExtendedApplication application = (ExtendedApplication) context.getApplicationContext();
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
