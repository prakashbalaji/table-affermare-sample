package com.rest.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

public class LibraryConfiguration extends Configuration {
    private DataSourceFactory libraryDBConfig;
    @NotEmpty
    private String dummy;


    @JsonProperty
    public String getDummy() {
        return dummy;
    }

    @JsonProperty
    public void setDummy(String dummy) {
        this.dummy = dummy;
    }

    public DataSourceFactory getLibraryDBConfig() {
        return libraryDBConfig;
    }
}
