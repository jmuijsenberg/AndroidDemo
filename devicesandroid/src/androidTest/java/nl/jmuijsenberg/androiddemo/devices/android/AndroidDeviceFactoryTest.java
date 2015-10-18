package nl.jmuijsenberg.androiddemo.devices.android;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class AndroidDeviceFactoryTest extends ApplicationTestCase<Application> {
    public AndroidDeviceFactoryTest() {
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
        AndroidDeviceFactory device = new AndroidDeviceFactory();
        assertNotNull(device);
    }
}