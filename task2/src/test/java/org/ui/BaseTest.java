package org.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    @BeforeTest
    static void setupAllureReports() {
        Configuration.pageLoadTimeout = 120000;
        Configuration.timeout = 20000;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(false)
                .savePageSource(true)
        );
    }
}
