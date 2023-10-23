package pages;


import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


//This script will login into Amazon and store the cookies for later use.
public class LoginCookies {

    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;


    public void setUpBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(true));
        context = browser.newContext(new Browser.NewContextOptions()
                .setStorageStatePath(Paths.get("auth.json")));
        page = context.newPage();
    }

    public void closeBrowser() {
        page.close();
    }

    public void goToPage() {
        page.navigate("https://www.amazon.com/");
        //assert the title of the page
        assertThat(page).hasTitle("Amazon.com. Spend less. Smile more.");

    }

    public boolean loggedIn(){
        Locator geoLocationLabel = page.locator("#nav-global-location-popover-link");
        String s = geoLocationLabel.innerHTML();
       return s.contains("Mitchell");
    }
    public void clickLogIn() {
        //assert no user is logged in
        Locator signInLabel = page.locator("#nav-link-accountList-nav-line-1");
        assertThat(signInLabel).hasText("Hello, sign in");
        page.locator("#nav-signin-tooltip").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Sign in")).click();
    }

    public void enterUsername(String username) {
        page.getByLabel("Email or mobile phone number").fill(username);
    }

    public void clickContinue() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).click();

    }

    public void enterPassword(String password) {
        page.getByLabel("Password").fill(password);
    }

    public void clickStayLoggedIn() {
        Locator rememberMeCheckBox = page.locator("input[name=\"rememberMe\"]");
        rememberMeCheckBox.check();
        //assert that remember me is checked
        assertThat(rememberMeCheckBox).isChecked();
    }

    public void clickOnSignIn() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign in")).click();
    }

    public void storeCookies() {
      context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("auth.json")));
    }
}


