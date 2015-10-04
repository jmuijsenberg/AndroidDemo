package nl.jmuijsenberg.androiddemo.viewmodels;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MainViewModelTest {
    @Before
    public void before() {
    }

    @After
    public void after() {
    }

    @Test
    public void testConstructor() {
        MainViewModel mainViewModel = new MainViewModel();

        assertNotNull(mainViewModel);
    }
}
