package nl.jmuijsenberg.androiddemo.devices.android;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class NativeDeviceTest2 extends ApplicationTestCase<Application> {
    public NativeDeviceTest2() {
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
        NativeDevice device = new NativeDevice();
        assertNotNull(device);
    }
}
