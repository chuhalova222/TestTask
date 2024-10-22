package org.ui;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.ui.pageobject.MainPage;

import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;

@Slf4j
public class Task2Test extends BaseTest {

    private static final double FROM_VALUE = 1.5;
    private static final double TO_VALUE = 3.34;
    private static final String SITE_URL = "https://s.gsb.co.zm/";

    @Test
    public void filterUpcomingEvents() {
//      1. Navigate to: https://s.gsb.co.zm/(SITE_URL)
        var mainPage = open(SITE_URL, MainPage.class);
//      2. Choose "Upcoming" tab
        var upcomingTab = mainPage.openUpcomingTab();
//      3. Search for all events, which have odds value between 1.5(FROM_VALUE) - 3.34(TO_VALUE)
        var getGameNameCombinedWithOdds = upcomingTab.getGameNameCombinedWithOdds();
//      4. Print filtered events
        printFilteredEvents(getGameNameCombinedWithOdds);
//      4.1. add validation
//      I don't really get what need to be validated. Just to save time and do not ask a lot of questions will implement verifications that fail test if at least one
//      game does not contain required odd:
        assertAllEventsInRequiredOddsLimit(getGameNameCombinedWithOdds);
    }

    private void assertAllEventsInRequiredOddsLimit(Map<String, List<String>> getGameNameCombinedWithOdds) {
        var softAssert = new SoftAssert();
        getGameNameCombinedWithOdds.forEach((key, value) ->
                softAssert.assertTrue(isBetweenRequiredValues(value),
                format("Event: %s has no odds between %s and %s\n Odds: %s\n", key, FROM_VALUE, TO_VALUE, value)));
        softAssert.assertAll();
    }

    private void printFilteredEvents(Map<String, List<String>> getGameNameCombinedWithOdds) {
        getGameNameCombinedWithOdds.forEach((key, value) -> {
            if (isBetweenRequiredValues(value))
                log.info("Event: {} has odds between {} and {}\n Odds: {}\n", key, FROM_VALUE, TO_VALUE, value);
        });
    }

    private boolean isBetweenRequiredValues(List<String> value) {
        return value.stream().anyMatch(
                valueItem -> {
                    var oddValue = Double.parseDouble(valueItem);
                    return FROM_VALUE < oddValue && oddValue < TO_VALUE;
                }
        );
    }
}
