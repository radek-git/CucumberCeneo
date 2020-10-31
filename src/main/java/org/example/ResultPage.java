package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.example.conditions.HasClass;
import org.example.fileUtils.CsvUtils;
import org.example.model.MyTime;
import org.example.model.Offer;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;
import static org.example.conditions.ConditionsUtils.containingClass;
import static org.example.fileUtils.CsvUtils.saveToFile;

public class ResultPage extends BasePage {

    static class XPATH{
        public static final String PRODUCT_DIV = "(//div[@class='category-list-body js_category-list-body js_search-results']/div[@class='cat-prod-row js_category-list-item js_clickHashData js_man-track-event '])";
        public static final String NEXT_PAGE_BUTTON = "//a[@class='pagination__item pagination__next']";
        public static final String PRODUCT_NAME_BUTTON = "(//div[@class='category-list-body js_category-list-body js_search-results']/div[@class='cat-prod-row js_category-list-item js_clickHashData js_man-track-event '])[%d]/div/div[@class='cat-prod-row__content']/div/div/div/strong/a";
        public static final String GO_TO_SHOP_LINK = "//div[@class='btn-compare-outer']/a[contains(@class, 'go-to-shop')]";
    }

    @Override
    public void waitForPageToFinishLoading() {
        WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), 5000);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("js_category-list-item")));
    }

    @Override
    public void init() {

    }

    public List<Offer> getOffers() throws InterruptedException {
        List<Offer> offersList = new ArrayList<>();
        boolean isNextButtonDisplayed;

        do {
            int numberOfProducts = $$x(XPATH.PRODUCT_DIV).size();
            for (int i = 1; i <= numberOfProducts; i++) {
                SelenideElement productOffersButton = $x(format("(//div[@class='btn-compare-outer'])[%d]", i));
                if (productOffersButton.is(containingClass("go-to-shop"))) {
                    SelenideElement singleProductName = $x(format(XPATH.PRODUCT_NAME_BUTTON, i)).waitUntil(Condition.visible, 5000);
                    // cena
                    BigDecimal price = new BigDecimal("0.0");

                    offersList.add(new Offer("UNKNOWN", singleProductName.getText(), price, LocalDate.now()));
                } else {
                    actions().keyDown(Keys.LEFT_CONTROL).click(productOffersButton).keyUp(Keys.LEFT_CONTROL).build().perform();

                    switchTo().window(1);

                    ProductPage productPage = new ProductPage();
                    List<Offer> offers = productPage.getOffers();
                    saveToFile("offers.csv", Offer.class, offers);

                    offersList.addAll(offers);

                    closeWindow();
                    switchTo().window(0);
                }
            }
            SelenideElement nextButton = $x(XPATH.NEXT_PAGE_BUTTON);
            isNextButtonDisplayed = nextButton.exists();
            if (nextButton.isDisplayed()) {
                nextButton.click();
            }
        } while (isNextButtonDisplayed);

        return offersList;
    }
}
