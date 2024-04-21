package com.example.selenium.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.example.selenium.services.WebDriverService;
import com.example.selenium.util.CsvUtils;
import com.opencsv.exceptions.CsvValidationException;

import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Slf4j
public class ExportPage extends BasePage {

    public ExportPage(WebDriver driver) {
        super(driver, "/export");
    }

    @FindBy(css = "button[value=csv]")
    private WebElement exportToCsvButton;

    @FindBy(css = "form[action*='csv']")
    private WebElement csvForm;

    @FindBy(css = "form[action*='csv'] input[name='_token']")
    private WebElement csvHiddenInput;

    public List<List<String>> exportToCsv() throws IOException, CsvValidationException {
        log.info("Export links to csv");
        String url = csvForm.getAttribute("action");
        String token = csvHiddenInput.getAttribute("value");
        String cookies = WebDriverService.getCookies();
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("_token", token)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .header("Cookie", cookies)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + response);
            ResponseBody responseBody = response.body();
            if (responseBody == null) {
                throw new IllegalStateException("Response doesn't contain a file");
            }
            return CsvUtils.readCsvFile(responseBody.charStream());
        }
    }
}
