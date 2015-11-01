package nl.jmuijsenberg.androiddemo.repository.sqlite;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.RenamingDelegatingContext;

import java.util.List;

import nl.jmuijsenberg.androiddemo.entities.Student;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxJvmTestSchedulers;
import nl.jmuijsenberg.androiddemo.util.java.rxjava.RxSchedulers;
import rx.observers.TestSubscriber;

public class RepositorySqliteTest extends ApplicationTestCase<Application> {
    private RxSchedulers mSchedulers = new RxJvmTestSchedulers();
    private RepositorySqlite mRepository;

    private final static String FIRST_NAME1 = "Johan";
    private final static String LAST_NAME1 = "vdM";

    private final static String FIRST_NAME2 = "Kees";
    private final static String LAST_NAME2 = "K";

    private final static String FIRST_NAME3 = "Klaas";
    private final static String LAST_NAME3 = "V";

    public RepositorySqliteTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test");

        mSchedulers = new RxJvmTestSchedulers();
        mRepository = new RepositorySqlite(context, mSchedulers);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testInitialEmpty()
    {
        List<Student> students = getStudentsFromDatabase();
        assertEquals(0, students.size());
    }

    public void testAddUpdateDelete()
    {
        List<Student> studentsInitial = getStudentsFromDatabase();
        assertEquals(0, studentsInitial.size());

        Student student1 = new Student();
        student1.setFirstName(FIRST_NAME1);
        student1.setLastName(LAST_NAME1);
        addStudentToDatabase(student1);

        List<Student> studentsAfterFirstAdd = getStudentsFromDatabase();
        assertEquals(1, studentsAfterFirstAdd.size());
        assertEquals(FIRST_NAME1, studentsAfterFirstAdd.get(0).getFirstName());
        assertEquals(LAST_NAME1, studentsAfterFirstAdd.get(0).getLastName());

        Student student2 = new Student();
        student2.setFirstName(FIRST_NAME2);
        student2.setLastName(LAST_NAME2);
        addStudentToDatabase(student2);

        List<Student> studentsAfterSecondAdd = getStudentsFromDatabase();
        assertEquals(2, studentsAfterSecondAdd.size());
        assertEquals(FIRST_NAME1, studentsAfterSecondAdd.get(0).getFirstName());
        assertEquals(LAST_NAME1, studentsAfterSecondAdd.get(0).getLastName());
        assertEquals(FIRST_NAME2, studentsAfterSecondAdd.get(1).getFirstName());
        assertEquals(LAST_NAME2, studentsAfterSecondAdd.get(1).getLastName());

        studentsAfterSecondAdd.get(0).setFirstName(FIRST_NAME3);
        studentsAfterSecondAdd.get(0).setLastName(LAST_NAME3);
        updateStudent(studentsAfterSecondAdd.get(0));

        List<Student> studentsAfterUpdate = getStudentsFromDatabase();
        assertEquals(2, studentsAfterUpdate.size());
        assertEquals(FIRST_NAME3, studentsAfterUpdate.get(0).getFirstName());
        assertEquals(LAST_NAME3, studentsAfterUpdate.get(0).getLastName());
        assertEquals(FIRST_NAME2, studentsAfterUpdate.get(1).getFirstName());
        assertEquals(LAST_NAME2, studentsAfterUpdate.get(1).getLastName());

        deleteStudent(studentsAfterSecondAdd.get(1));

        List<Student> studentsAfterDelete = getStudentsFromDatabase();
        assertEquals(1, studentsAfterDelete.size());
        assertEquals(FIRST_NAME3, studentsAfterSecondAdd.get(0).getFirstName());
        assertEquals(LAST_NAME3, studentsAfterSecondAdd.get(0).getLastName());
    }

    private List<Student> getStudentsFromDatabase()
    {
        TestSubscriber<List<Student>> testSubscriber = new TestSubscriber<>();
        mRepository.getStudents().subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        return testSubscriber.getOnNextEvents().get(0);
    }

    private void addStudentToDatabase(Student student)
    {
        TestSubscriber<Boolean> testSubscriber = new TestSubscriber<>();
        mRepository.addStudent(student).subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        Boolean success = testSubscriber.getOnNextEvents().get(0);
        assertTrue(success);
    }

    private void deleteStudent(Student student) {
        TestSubscriber<Boolean> testSubscriber = new TestSubscriber<>();
        mRepository.deleteStudent(student).subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        Boolean success = testSubscriber.getOnNextEvents().get(0);
        assertTrue(success);
    }

    private void updateStudent(Student student) {
        TestSubscriber<Boolean> testSubscriber = new TestSubscriber<>();
        mRepository.updateStudent(student).subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        Boolean success = testSubscriber.getOnNextEvents().get(0);
        assertTrue(success);
    }
}