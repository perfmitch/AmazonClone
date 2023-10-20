package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.SearchPage;

public class SearchSteps {

    SearchPage page = new SearchPage();

    @Before
    public void setUp(){
        page.setUpBrowser();
    }

    @After
    public void cleanUp(){
        page.closeBrowser();
    }


    @Given("I am logged in on Amazon")
    public void iAmLoggedInOnAmazon() {
        page.goToAmazon();
    }

    @Then("I search for the following products")
    public void iSearchForTheFollowingProducts(DataTable product) {
        String productName = product.cell(1,0);
        page.searchProduct(productName);
    }

    @And("I click on the Nth product")
    public void iClickOnTheNthProduct(DataTable number) {
        String num = number.cell(1,0);
        int productNum = Integer.parseInt(num);
        page.clickOnProduct(productNum);
    }

    @Given("I am on the product page")
    public void iAmOnTheProductPage() {
        page.confirmProductPage();
    }

    @Then("I select the quantity")
    public void iSelectTheQuantity(DataTable quantity) {
        String amount = quantity.cell(1,0);
        page.selectQuantity(amount);
    }

    @And("I click on add to cart")
    public void iClickOnAddToCart() {
        page.clickAddToCart();
    }

}


