package nl.jmuijsenberg.androiddemo.app;

import android.app.Application;

import com.facebook.stetho.Stetho;

import nl.jmuijsenberg.androiddemo.BuildConfig;

public class ExtendedApplication extends Application {
    @Override
    public void onCreate()
    {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Stetho.initialize(
                    Stetho.newInitializerBuilder(this)
                            .enableDumpapp(
                                    Stetho.defaultDumperPluginsProvider(this))
                            .enableWebKitInspector(
                                    Stetho.defaultInspectorModulesProvider(this))
                            .build());
        }
    }
}
