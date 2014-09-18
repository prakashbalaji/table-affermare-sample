package com.rest.steps;

import com.rest.application.LibraryApplication;
import com.rest.request.JerseyClient;
import com.table.datasource.JdbiAdapter;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.skife.jdbi.v2.Handle;

import static com.rest.integration.LibraryCucumberTest.RULE;

public class ApplicationSpecificSteps {


    private Handle handle;

    @Given("^I setup data$")
    public void I_setup_data() throws Throwable {
        // dummy step
    }

    @Then("^I start server$")
    public void I_start_server() throws Throwable {
        JerseyClient.initialize(String.format("http://localhost:%d/", RULE.getLocalPort()));
        LibraryApplication application = RULE.getApplication();
        handle = application.getHandle();
        JdbiAdapter.initialize(handle);
    }

    @After
    public void tearDown() throws Exception {
        LibraryApplication application = RULE.getApplication();
        handle.execute("delete from authors");
        handle.close();

    }
}
