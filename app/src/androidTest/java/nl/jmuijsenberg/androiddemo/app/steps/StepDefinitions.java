package nl.jmuijsenberg.androiddemo.app.steps;

import android.test.ActivityInstrumentationTestCase2;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nl.jmuijsenberg.androiddemo.app.activities.NavigationDrawerActivity;

public class StepDefinitions   extends ActivityInstrumentationTestCase2<NavigationDrawerActivity> {
    public StepDefinitions() {
        super(NavigationDrawerActivity.class);
    }

    @Given("^The user has started the application$")
    public void The_user_has_started_the_application() throws Throwable {
        assertNotNull(getActivity());
    }

    @When("^The user selects apply$")
    public void The_user_selects_apply() throws Throwable {
    }

    @Then("^The system shows it is done$")
    public void The_system_shows_it_is_done() throws Throwable {
    }

    @Override
    public NavigationDrawerActivity getActivity() {
        return super.getActivity();
    }
}
