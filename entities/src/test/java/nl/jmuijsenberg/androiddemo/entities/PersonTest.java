package nl.jmuijsenberg.androiddemo.entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class PersonTest {
    @Before
    public void before() {
    }

    @After
    public void after() {
    }

    @Test
    public void testConstructor() {
        Person person = new Person();

        assertNotNull(person);
    }
}
