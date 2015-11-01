package nl.jmuijsenberg.androiddemo.entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class StudentTest {
    @Before
    public void before() {
    }

    @After
    public void after() {
    }

    @Test
    public void testConstructor() {
        Student student = new Student();

        assertNotNull(student);
    }
}
