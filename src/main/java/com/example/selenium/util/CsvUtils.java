package com.example.selenium.util;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CsvUtils {

    public List<List<String>> readCsvFile(Reader reader)
            throws IOException, CsvValidationException {
        List<List<String>> records = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(reader);) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        }
        return records;
    }
}
