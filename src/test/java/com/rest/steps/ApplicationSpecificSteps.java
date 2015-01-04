package com.rest.steps;

import com.rest.application.LibraryApplication;
import com.rest.request.JerseyClient;
import com.table.datasource.SpringAdapter;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.dropwizard.db.DataSourceFactory;
import org.skife.jdbi.v2.Handle;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import static com.rest.integration.LibraryCucumberTest.RULE;

public class ApplicationSpecificSteps {


    private Handle handle;

    @Given("^I setup data$")
    public void I_setup_data() throws Throwable {
    }

    @Then("^I start server$")
    public void I_start_server() throws Throwable {

        DataSourceFactory libraryDBConfig = RULE.getConfiguration().getLibraryDBConfig();
        DriverManagerDataSource dataSource = new DriverManagerDataSource(libraryDBConfig.getUrl(),libraryDBConfig.getUser(), libraryDBConfig.getPassword());
        JerseyClient.initialize(String.format("http://localhost:%d/", RULE.getLocalPort()));
        LibraryApplication application = RULE.getApplication();

        handle = application.getHandle();
        SpringAdapter.initialize(new JdbcTemplate(dataSource));
    }

    @After
    public void tearDown() throws Exception {
        LibraryApplication application = RULE.getApplication();
        handle.execute("delete from authors");
        handle.close();

    }
}
