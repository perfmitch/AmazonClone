package steps;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.SkipException;
import pages.LoginCookies;

public class LoginSteps {
    LoginCookies login = new LoginCookies();

    @Before
    public void setUp() {
        login.setUpBrowser();
    }

    @After
    public void cleanUp() {
       login.closeBrowser();
    }

    @Given("I am on Amazon homepage")
    public void iAmOnAmazonHomepage() {
        login.goToPage();
        //skip if your're logged in already, assume that you're logged in
        if(login.loggedIn()){
            throw new SkipException("Skipping this exception");
        }
    }

    @And("I click on Log in")
    public void iClickOnLogIn() {
        login.clickLogIn();
    }

    @Then("I enter the username")
    public void iEnterTheUsername(DataTable username) {
        String user = username.cell(1, 0);
        login.enterUsername(user);
    }

    @And("I click on continue")
    public void iClickOnContinue() {
        login.clickContinue();
    }

    @Then("I enter the password")
    public void iEnterThePassword(DataTable password) {
        String pass = password.cell(1, 0);
        login.enterPassword(pass);
    }

    @And("I click on Stay Logged In")
    public void iClickOnStayLoggedIn() {
        login.clickStayLoggedIn();
    }

    @Then("I click on Sign In")
    public void iClickOnSignIn() {
        login.clickOnSignIn();
    }

    @And("I store the cookies in auth.json")
    public void iStoreTheCookiesInAuthJson() {
        login.storeCookies();
    }
}
