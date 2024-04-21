package com.example.selenium;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;

@LoadPolicy(LoadType.MERGE)
@Sources({
    "system:env",
    "system:properties",
    "file:src/test/resources/test.properties"
})
public interface TestConfig extends Config {

    String appUrl();
    String appUser();
    String appPassword();
    String apiUrl();
    String apiToken();
    String dbUrl();
    String dbUser();
    String dbPassword();
    String browser();
    String hubUrl();

    static TestConfig get() {
        return ConfigFactory.create(TestConfig.class);
    }
}
