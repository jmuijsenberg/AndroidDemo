package nl.jmuijsenberg.androiddemo.app.steps;

import cucumber.api.CucumberOptions;

@CucumberOptions(
        plugin = {"pretty", "json:/data/data/nl.jmuijsenberg.androiddemo.app/reports/cucumber.json"},
        features = "features",
        monochrome = false,
        strict = false,
        tags = { "~@outline", "~@draft", "~@ignore", "~@nightlybuild", "~@manual" },
        //tags = { "@runthis"},
        dryRun = false)
public class Config {
}
