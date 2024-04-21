package com.example.selenium.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.ibatis.jdbc.ScriptRunner;

import com.example.selenium.TestConfig;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class DatabaseService {

    private TestConfig config = TestConfig.get();
    
    public void runScript(String filePath) throws SQLException, IOException {
        log.info("Run SQL script from {}", filePath);
        try (Connection con = DriverManager.getConnection(config.dbUrl(), config.dbUser(), config.dbPassword())) {
            ScriptRunner sr = new ScriptRunner(con);
            sr.setLogWriter(null);
            try (Reader reader = new BufferedReader(new FileReader(filePath))) {
                sr.runScript(reader);
            }
        }
    }
}
