package org.ui.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import java.util.Map;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    public MainPage openUpcomingTab(){
        $(By.id("menusportbookprematch")).shouldBe(Condition.visible).click();
        return page(MainPage.class);
    }

    public Map<String, ElementsCollection> getEvents(){
        return $$(By.xpath("//tr[@class = 'event-row']"))
                .shouldHave(sizeGreaterThan(0))
                .stream().collect(
                        Collectors.toMap(
                                row -> row.$(By.xpath(".//div[contains(@class, 'general-info')]")).text(),
                                row -> row.$$(By.xpath(".//span[contains(@class, 'odds')]"))
                        )
                );
    }
}
