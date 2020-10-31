package org.example.conditions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Driver;
import org.openqa.selenium.WebElement;

public class HasClass extends Condition {

    private final String htmlClass;

    public HasClass(String htmlClass) {
        super("hasClass");

        this.htmlClass = htmlClass;
    }

    @Override
    public boolean apply(Driver driver, WebElement element) {
        return hasClass(element, htmlClass);
    }

    private boolean hasClass(WebElement element, String htmlClass) {
        String classes = element.getAttribute("class");
        for (String c : classes.split(" ")) {
            if (c.equals(htmlClass)) {
                return true;
            }
        }

        return false;
    }
}
