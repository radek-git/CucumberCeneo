package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.fileUtils.CsvUtils;
import org.example.model.Offer;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static org.example.fileUtils.CsvUtils.saveToFile;

public class SearchingProductSteps extends BaseTest{

    private HomePage homePage;
    private ResultPage resultPage;
    private List<Offer> offers;


    @Given("User opens page")
    public void user_opens_page() {
        open("https://www.ceneo.pl/");
    }

    @Given("User accepts cookies")
    public void user_accepts_cookies() {
        homePage = new HomePage();
        homePage.clickAcceptCookieButton();
    }

    @Given("User inputs query={string} and clicks Search button")
    public void user_inputs_query_and_clicks_search_button(String query) {
        homePage.inputQuery(query);
        homePage.clickSearchButton();
    }

    @When("User reads all data of products")
    public void user_reads_all_data_of_products() throws InterruptedException {
        resultPage = new ResultPage();
        offers = resultPage.getOffers();

    }

    @Then("Data is saved")
    public void data_is_saved() {
    }

}
