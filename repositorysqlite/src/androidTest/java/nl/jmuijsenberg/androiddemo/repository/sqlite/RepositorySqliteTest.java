package nl.jmuijsenberg.androiddemo.repository.sqlite;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.RenamingDelegatingContext;

import java.util.List;

import nl.jmuijsenberg.androiddemo.entities.Person;
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
        List<Person> persons = getPersonsFromDatabase();
        assertEquals(0, persons.size());
    }

    public void testAddUpdateDelete()
    {
        List<Person> personsInitial = getPersonsFromDatabase();
        assertEquals(0, personsInitial.size());

        Person person1 = new Person();
        person1.setFirstName(FIRST_NAME1);
        person1.setLastName(LAST_NAME1);
        addPersonToDatabase(person1);

        List<Person> personsAfterFirstAdd = getPersonsFromDatabase();
        assertEquals(1, personsAfterFirstAdd.size());
        assertEquals(FIRST_NAME1, personsAfterFirstAdd.get(0).getFirstName());
        assertEquals(LAST_NAME1, personsAfterFirstAdd.get(0).getLastName());

        Person person2 = new Person();
        person2.setFirstName(FIRST_NAME2);
        person2.setLastName(LAST_NAME2);
        addPersonToDatabase(person2);

        List<Person> personsAfterSecondAdd = getPersonsFromDatabase();
        assertEquals(2, personsAfterSecondAdd.size());
        assertEquals(FIRST_NAME1, personsAfterSecondAdd.get(0).getFirstName());
        assertEquals(LAST_NAME1, personsAfterSecondAdd.get(0).getLastName());
        assertEquals(FIRST_NAME2, personsAfterSecondAdd.get(1).getFirstName());
        assertEquals(LAST_NAME2, personsAfterSecondAdd.get(1).getLastName());

        personsAfterSecondAdd.get(0).setFirstName(FIRST_NAME3);
        personsAfterSecondAdd.get(0).setLastName(LAST_NAME3);
        updatePerson(personsAfterSecondAdd.get(0));

        List<Person> personsAfterUpdate = getPersonsFromDatabase();
        assertEquals(2, personsAfterUpdate.size());
        assertEquals(FIRST_NAME3, personsAfterUpdate.get(0).getFirstName());
        assertEquals(LAST_NAME3, personsAfterUpdate.get(0).getLastName());
        assertEquals(FIRST_NAME2, personsAfterUpdate.get(1).getFirstName());
        assertEquals(LAST_NAME2, personsAfterUpdate.get(1).getLastName());

        deletePerson(personsAfterSecondAdd.get(1));

        List<Person> personsAfterDelete = getPersonsFromDatabase();
        assertEquals(1, personsAfterDelete.size());
        assertEquals(FIRST_NAME3, personsAfterSecondAdd.get(0).getFirstName());
        assertEquals(LAST_NAME3, personsAfterSecondAdd.get(0).getLastName());
    }

    private List<Person> getPersonsFromDatabase()
    {
        TestSubscriber<List<Person>> testSubscriber = new TestSubscriber<>();
        mRepository.getPersons().subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        return testSubscriber.getOnNextEvents().get(0);
    }

    private void addPersonToDatabase(Person person)
    {
        TestSubscriber<Boolean> testSubscriber = new TestSubscriber<>();
        mRepository.addPerson(person).subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        Boolean success = testSubscriber.getOnNextEvents().get(0);
        assertTrue(success);
    }

    private void deletePerson(Person person) {
        TestSubscriber<Boolean> testSubscriber = new TestSubscriber<>();
        mRepository.deletePerson(person).subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        Boolean success = testSubscriber.getOnNextEvents().get(0);
        assertTrue(success);
    }

    private void updatePerson(Person person) {
        TestSubscriber<Boolean> testSubscriber = new TestSubscriber<>();
        mRepository.updatePerson(person).subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        Boolean success = testSubscriber.getOnNextEvents().get(0);
        assertTrue(success);
    }
}