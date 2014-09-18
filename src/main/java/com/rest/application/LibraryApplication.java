package com.rest.application;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.rest.configuration.LibraryConfiguration;
import com.rest.resource.AuthorResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

public class LibraryApplication extends Application<LibraryConfiguration> {

    private DBI dbi;

    @Override
    public void initialize(Bootstrap<LibraryConfiguration> bootstrap) {

    }

    @Override
    public void run(LibraryConfiguration configuration, Environment environment) throws Exception {
        dbi = getDBI(configuration, environment);
        final AuthorResource authorResource = new AuthorResource(dbi.open());
        environment.jersey().register(authorResource);
        environment.getObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

    }

    public DBI getDBI(LibraryConfiguration configuration, Environment environment) throws ClassNotFoundException {
        final DBIFactory factory = new DBIFactory();
        final DBI dbi = factory.build(environment, configuration.getLibraryDBConfig(), "libraryDB");
        return dbi;

    }

    public static void main(String[] args) throws Exception {
        new LibraryApplication().run(args);
    }

    public Handle getHandle (){
        return dbi.open();
    }
}
