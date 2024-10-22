package org.ui;

import com.codeborne.selenide.ElementsCollection;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import org.ui.pageobject.MainPage;

import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class Task2Test extends BaseTest {

    private static final double FROM_VALUE = 1.5;
    private static final double TO_VALUE = 3.34;

    @Test
    public void filterUpcomingEvents() {
        var mainPage = open("https://s.gsb.co.zm/", MainPage.class);
        var upcomingTab = mainPage.openUpcomingTab();
        var events = upcomingTab.getEvents();
        events.forEach((key, value) -> {
            var result = isBetweenFromAndToValues(value);
            result ? log.info("Event: " + key + " has odds between 1.5 and 3.34. Odds: " + value.texts())
                    : log.info("There aren't events that have odds between 1.5 and 3.34")
        });
    }

    private static boolean isBetweenFromAndToValues(ElementsCollection value) {
        return value.stream().anyMatch(
                valueItem -> {
                    var oddValue = Double.parseDouble(valueItem.text());
                    return FROM_VALUE < oddValue && oddValue < TO_VALUE;
                }
        );
    }
}
