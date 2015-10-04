package nl.jmuijsenberg.androiddemo.util.android;

import android.app.Application;
import android.test.ApplicationTestCase;

public class AndroidUtilTest extends ApplicationTestCase<Application> {
    public AndroidUtilTest() {
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
        AndroidUtil util = new AndroidUtil();
        assertNotNull(util);
    }
}