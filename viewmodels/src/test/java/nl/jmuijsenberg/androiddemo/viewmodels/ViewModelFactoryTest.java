package nl.jmuijsenberg.androiddemo.viewmodels;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ViewModelFactoryTest {
    @Before
    public void before() {
    }

    @After
    public void after() {
    }

    @Test
    public void testConstructor() {
        nl.jmuijsenberg.androiddemo.viewmodels.factory.ViewModelFactory mainViewModel = new nl.jmuijsenberg.androiddemo.viewmodels.factory.ViewModelFactory(null, null);

        assertNotNull(mainViewModel);
    }
}
