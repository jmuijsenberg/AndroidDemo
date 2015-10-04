package nl.jmuijsenberg.androiddemo.control;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import nl.jmuijsenberg.androiddemo.devices.Device;

import static org.junit.Assert.assertNotNull;

public class MainControllerTest {
    @Before
    public void before() {
    }

    @After
    public void after() {
    }

    @Test
    public void testConstructor() {
        MainController mainController = new MainController();

        assertNotNull(mainController);
    }
}
