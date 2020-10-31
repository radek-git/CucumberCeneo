package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.*;

public class HomePage {

    static class XPATH {
        public static final String INPUT_QUERY = "//input[@name='search-query']";
        public static final String SEARCH_BUTTON = "(//button[@type='submit'])[1]";
        public static final String COOKIE_DIV = "//div[@id='js_cookie-monster']";
        public static final String ACCEPT_COOKIE_BUTTON = "//div[@class='cookie-note']/p/button";
    }

    public HomePage inputQuery(String query) {
        $x(XPATH.INPUT_QUERY).val(query);
        return new HomePage();
    }

    public ResultPage clickSearchButton() {
        $x(XPATH.SEARCH_BUTTON).click();
        return new ResultPage();
    }

    public HomePage clickAcceptCookieButton() {
        $x(XPATH.COOKIE_DIV).waitUntil(Condition.visible, 5000);
        $x(XPATH.ACCEPT_COOKIE_BUTTON).click();
        return new HomePage();
    }
}
