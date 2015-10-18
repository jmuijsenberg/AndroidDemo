package nl.jmuijsenberg.androiddemo.repository.sqlite;

import android.app.Application;
import android.test.ApplicationTestCase;

public class RepositorySqliteTest extends ApplicationTestCase<Application> {
    public RepositorySqliteTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testConstructor() {
        RepositorySqlite repository = new RepositorySqlite(null, null);
        assertNotNull(repository);
    }
}