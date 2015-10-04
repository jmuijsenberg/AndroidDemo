package nl.jmuijsenberg.androiddemo.devices;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DeviceTest2 {
    @Before
    public void before() {
    }

    @After
    public void after() {
    }

    @Test
    public void testConstructor() {
        Device device = new Device();

        assertNotNull(device);
    }
}
