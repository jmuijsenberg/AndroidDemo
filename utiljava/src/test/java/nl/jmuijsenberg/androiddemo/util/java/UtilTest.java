package nl.jmuijsenberg.androiddemo.util.java;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class UtilTest {
    @Before
    public void before() {
    }

    @After
    public void after() {
    }

    @Test
    public void testConstructor() {
        Util util = new Util();

        assertNotNull(util);
    }
}
