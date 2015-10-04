package nl.jmuijsenberg.androiddemo.app;

import android.app.Application;
import android.test.ApplicationTestCase;

import nl.jmuijsenberg.androiddemo.devices.android.NativeDevice;

public class MainActivityTest extends ApplicationTestCase<Application> {
    public MainActivityTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testConstructor() {
        MainActivity activity = new MainActivity();
        assertNotNull(activity);
    }
}
