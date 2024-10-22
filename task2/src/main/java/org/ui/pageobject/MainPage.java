package org.ui.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private final ElementsCollection events = $$x("//tr[@class = 'event-row']");
    private final SelenideElement menuSportBookPrematch = $(By.id("menusportbookprematch"));
    private final SelenideElement showMoreBtn = $x("//*[@class='show-more']");

    private static final String ODDS_XPATH = ".//span[contains(@class, 'odds')]";
    private static final String GENERAL_INFO_XPATH = ".//div[contains(@class, 'general-info')]";

    public MainPage openUpcomingTab() {
        menuSportBookPrematch.shouldBe(Condition.visible).click();
        return this;
    }

    public Map<String, List<String>> getGameNameCombinedWithOdds() {
        events.shouldHave(sizeGreaterThan(0));
        while (showMoreBtn.isDisplayed()) {
            showMoreBtn.scrollTo().click();
            // Small sleep here to handle case of fast java execution. Without this code will go inside the loop for additional redundant time.
            // NOT THE BEST PRACTICE AT ALL. I know this.
            sleep(200);
        }
        return events.stream().collect(
                Collectors.toMap(
                        row -> row.$x(GENERAL_INFO_XPATH).text().replace("\n", " "),
                        row -> row.$$x(ODDS_XPATH).stream().map(SelenideElement::getText).filter(item -> !item.isEmpty()).toList()
                )
        );
    }

}
