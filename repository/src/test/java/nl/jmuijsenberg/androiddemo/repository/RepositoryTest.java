package nl.jmuijsenberg.androiddemo.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class RepositoryTest {
    @Before
    public void before() {
    }

    @After
    public void after() {
    }

    @Test
    public void testConstructor() {
        Repository repository = new Repository();

        assertNotNull(repository);
    }
}
