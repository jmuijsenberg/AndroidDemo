package nl.jmuijsenberg.androiddemo.app.options;

import cucumber.api.CucumberOptions;

@CucumberOptions(
        features = "features", // Test scenarios
        glue = {"nl.jmuijsenberg.androiddemo.app.steps"}, // Steps definitions
        format = {"pretty" },
        tags={"@requirement"}
)
public class Config {
}
