package nl.jmuijsenberg.androiddemo.devices;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DeviceFactoryTest {
    @Before
    public void before() {
    }

    @After
    public void after() {
    }

    @Test
    public void testConstructor() {
        DeviceFactory device = new DeviceFactory();

        assertNotNull(device);
    }
}
