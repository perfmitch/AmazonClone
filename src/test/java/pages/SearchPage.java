package pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.hamcrest.core.StringContains;
import org.testng.Assert;


import java.nio.file.Paths;


public class SearchPage {

    Playwright playwright;
    Browser browser;
    BrowserContext context;
    static Page page;



    public void setUpBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false));
        context = browser.newContext(new Browser.NewContextOptions()
                .setStorageStatePath(Paths.get("auth.json")));
        page = context.newPage();
    }

    public void closeBrowser() {
        page.close();
    }


    public void goToAmazon() {
        page.navigate("https://www.amazon.com/");
        //assert that page has name to verify login
        Locator geoLocationLabel = page.locator("#nav-global-location-popover-link");
        PlaywrightAssertions.assertThat(geoLocationLabel).containsText("Mitchell");
    }

    public void searchProduct(String product) {
        Locator searchBox = page.getByPlaceholder("Search Amazon");
        searchBox.fill(product);
        searchBox.press("Enter");
    }


    public void clickOnProduct(int num) {
        String xPath = String.format("xpath=(//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal'])[%s]", num);
        page.locator(xPath).click();
    }

    public void confirmProductPage() {
        Assert.assertEquals(page.url(), StringContains.containsString("dp"));
    }


    public void selectQuantity(String amount) {
        //click on quantity dropbox
        page.waitForTimeout(3000);
        page.locator("(//span[@id='a-autoid-0-announce'])[1]").click();

        //click on the number of items, amazon like to change the type
        //rows start at 0, must subtract 1 from amount
        int newNum = Integer.parseInt(amount)-1;
        String dropbox1ID = String.format("//a[@id='quantity_%s']", newNum);
        String dropbox2ID = String.format("/a[@id='rcxsubsQuan_%s']", newNum);
        Locator dropbox1 = page.locator(dropbox1ID);
        Locator dropbox2 = page.locator(dropbox2ID);
        if(dropbox1.isVisible()){
            dropbox1.click();
        }else if(dropbox2.isVisible()){
            dropbox2.click();
        }

    }


    public void clickAddToCart() {
        page.waitForTimeout(2000);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to Cart")).first().click();
    }
}
