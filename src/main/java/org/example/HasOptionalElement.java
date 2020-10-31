package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$x;

public interface HasOptionalElement {

    default void clickIfVisible(String xpath) {
        if (isElementVisible(xpath)) {
            clickElement(xpath);
        }
    }

    default boolean isElementVisible(String xpath) {
        WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), 5);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    default void clickElement(String xpath) {
        $x(xpath).click();
    }
}
