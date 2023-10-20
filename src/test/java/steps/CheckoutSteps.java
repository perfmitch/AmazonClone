package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CheckoutPage;

public class CheckoutSteps {
    CheckoutPage page = new CheckoutPage();

    @Before
    public void setUp(){
        page.setUpBrowser();
    }

    @After
    public void cleanUp(){
        page.closeBrowser();
    }

    @Given("I am on the cart page")
    public void iAmOnTheCartPage() {
        page.goToCart();
    }

    @When("I click on Proceed to Checkout")
    public void iClickOnProceedToCheckout() {
        page.clickOnCheckout();
    }

    @Then("I change the address")
    public void iChangeTheAddress() {
        page.clickOnChangeAddress();
    }

    @And("I click on add a new address")
    public void iClickOnAddANewAddress() {
        page.clickOnAddANewAddress();
    }

    @Then("I fill out the address form with the details")
    public void iFillOutTheAddressFormWithTheDetails(DataTable dt) {
        String name = dt.cell(1,0);
        String phone = dt.cell(1,1);
        String address1 = dt.cell(1,2);
        String address2 = dt.cell(1,3);
        String city = dt.cell(1,4);
        String state = dt.cell(1,5);
        String zipcode = dt.cell(1,6);
        page.fillAddress(name, phone, address1, address2, city, state, zipcode);
    }

    @And("I save the information to use the address")
    public void iSaveTheInformationToUseTheAddress() {
        page.saveAddress();
    }

    @Then("I click add a new card")
    public void iClickAddANewCard() {
        page.clickOnAddCard();
    }

    @And("I fill out the card form with the details")
    public void iFillOutTheCardFormWithTheDetails(DataTable dt) {
        String number = dt.cell(1,0);
        String name = dt.cell(1,1);
        String month = dt.cell(1,2);
        String year = dt.cell(1,3);
        String cvv = dt.cell(1,4);
        page.fillCard(number,name,month,year,cvv);

    }

    @Then("I save the information to use this payment method")
    public void iSaveTheInformationToUseThisPaymentMethod() {
        page.addCard();
    }
}
