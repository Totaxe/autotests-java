package com.example.selenium;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;

import com.example.selenium.services.TestDataService;

public class BaseTest {
    
    protected TestConfig config = TestConfig.get();

    @BeforeEach
    protected void setupDatabase() throws SQLException, IOException {
        TestDataService.loadInitialData();
    }
}
