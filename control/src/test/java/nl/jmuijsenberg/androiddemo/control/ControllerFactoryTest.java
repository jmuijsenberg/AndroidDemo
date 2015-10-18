package nl.jmuijsenberg.androiddemo.control;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ControllerFactoryTest {
    @Before
    public void before() {
    }

    @After
    public void after() {
    }

    @Test
    public void testConstructor() {
        ControllerFactory mainController = new ControllerFactory(null, null, null);

        assertNotNull(mainController);
    }
}
