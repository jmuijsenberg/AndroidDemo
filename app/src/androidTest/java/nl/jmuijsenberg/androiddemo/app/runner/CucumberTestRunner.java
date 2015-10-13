package nl.jmuijsenberg.androiddemo.app.runner;

import android.os.Bundle;
import android.support.test.runner.MonitoringInstrumentation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cucumber.api.android.CucumberInstrumentationCore;
import nl.jmuijsenberg.androiddemo.app.BuildConfig;

public class CucumberTestRunner extends MonitoringInstrumentation {
    static {
        System.setProperty("jacoco-agent.destfile", "/data/data/nl.jmuijsenberg.androiddemo.app/coverage.ec");
    }

    private final CucumberInstrumentationCore instrumentationCore = new CucumberInstrumentationCore(this);
    private static final String CUCUMBER_TAGS_KEY = "tags";

    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);

        // Read tags passed as parameters and overwrite @CucumberOptions tags inside CucumberTestCase.java
        final String tags = BuildConfig.TEST_TAGS;
        if (!tags.isEmpty()) {
            // Reformat tags list to separate items with '--' as expected by Cucumber library, see method
            // cucumber-android-1.2.2.jar\cucumber\runtime\android\Arguments.class @ appendOption()
            bundle.putString(CUCUMBER_TAGS_KEY, tags.replaceAll(",", "--").replaceAll("\\s",""));
        }

        instrumentationCore.create(bundle);
        start();
    }

    @Override
    public void onStart() {
        waitForIdleSync();
        instrumentationCore.start();
    }

    @Override
    public void finish(int resultCode, Bundle results){
        try {
            Class rt = Class.forName("org.jacoco.agent.rt.RT");
            Method getAgent = rt.getMethod("getAgent");
            Method dump = getAgent.getReturnType().getMethod("dump", boolean.class);
            Object agent = getAgent.invoke(null);
            dump.invoke(agent, false);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        super.finish(resultCode, results);
    }
}
