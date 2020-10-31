package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.example.model.MyTime;
import org.example.model.Offer;
import org.example.model.ProductInfo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;
import static org.example.ProductPage.XPATH.*;

public class ProductPage extends BasePage implements HasOptionalElement {

    static class XPATH {
        public static final String PRODUCT_NAME = "//div[@class='product-top-2020__product-info']/div/h1";
        public static final String PRODUCT_PRICE = "(//div[@class='product-offer-2020__container clickable-offer js_offer-container-click  js_product-offer']/div[@class='product-offer-2020__product js_product-offer-2020__product js_productName specific-variant-content']/div[@class='product-offer-2020__product__price']/a/span/span[@class='price'])[%d]";
        public static final String PRICE_FILTER = "//dl[@class='page-tab-filters']/dd/div[@class='dropdown-wrapper']/a";
        public static final String PRICE_ASCENDING = "(//div[@class='dropdown-menu__group']/a[contains(text(), 'od najni')])[3]";
        public static final String SHOP_NAME = "(//div[@class='product-offer-2020__store']/div/a/img)[%d]";
        public static final String OFFER_DIV = "//ul[contains(@class, 'product-offers-2020__list')]/li[@class='product-offers-2020__list__item js_product-offer']";
        public static final String POPUP_CLOSE_BUTTON = "//div[@id='notifications-toast']/span[@class='close-dialog']";
        public static final String POPUP_DIV = "//div[@id='notifications-toast']";
        public static final String SHOW_MORE_OFFERS_BUTTON = "//div[contains(@class, 'show-remaining-offers')]/a";
    }

    @Override
    public void waitForPageToFinishLoading() {

    }

    @Override
    public void init() {
        System.out.println("ProductPage before init()");
        clickIfVisible(POPUP_CLOSE_BUTTON);
        clickIfVisible(SHOW_MORE_OFFERS_BUTTON);
        System.out.println("ProductPage after init()");
    }

    public ProductInfo getProductInfo() {
        $x(XPATH.PRICE_FILTER).waitUntil(Condition.visible, 5000).click();
        $x(XPATH.PRICE_ASCENDING).waitUntil(Condition.visible, 5000).click();

        String name = $x(XPATH.PRODUCT_NAME).getText();
        BigDecimal price = new BigDecimal($x(XPATH.PRODUCT_PRICE).waitUntil(Condition.visible, 5000).getText().replace(",", "."));
        String shopName = $x(XPATH.SHOP_NAME).getAttribute("alt");
        return new ProductInfo(shopName, name, price, LocalDate.now(), MyTime.now());
    }


    public List<Offer> getOffers() {
        System.out.println("ProductPage before getOffers()");
        List<SelenideElement> elements = $$x(OFFER_DIV);
        List<Offer> offers = new ArrayList<>();
        int numberOfOffers = elements.size();

        for (int i = 1; i <= numberOfOffers; i++) {
            String shopName = $x(format(SHOP_NAME, i)).getAttribute("alt");
            String productName = $x(PRODUCT_NAME).getText();
            BigDecimal price = new BigDecimal($x(format(PRODUCT_PRICE, i)).getText().replace(",", "."));

            Offer offer = new Offer(shopName, productName, price, LocalDate.now()).removePolish();
            offers.add(offer);
        }

        System.out.println("ProductPage after getOffers()");
        return offers;
    }



}
