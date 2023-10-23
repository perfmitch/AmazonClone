package pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Paths;


public class CheckoutPage {
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


    public void goToCart() {
        page.navigate("https://www.amazon.com/cart");
        PlaywrightAssertions.assertThat(page).hasTitle("Amazon.com Shopping Cart");
    }


    public void clickOnCheckout() {
        page.locator("//input[@name='proceedToRetailCheckout']").click();
    }


    public void clickOnChangeAddress() {
        page.locator("//a[@id='addressChangeLinkId']").click();
    }

    public void clickOnAddANewAddress() {
        page.locator("//a[@id='add-new-address-popover-link']").click();

    }


    public void fillAddress(String name, String phone, String address1, String address2, String city, String state, String zip) {
        //name input
        page.locator("//input[@id='address-ui-widgets-enterAddressFullName']").fill(name);
        //phone input
        page.locator("//input[@id='address-ui-widgets-enterAddressPhoneNumber']").fill(phone);
        //address 1 input
        page.locator("//input[@id='address-ui-widgets-enterAddressLine1']").fill(address1);
        //address 2 input
        if(address2 == null){
        page.locator("//input[@id='address-ui-widgets-enterAddressLine2']").fill(" ");
        }else{
            page.locator("//input[@id='address-ui-widgets-enterAddressLine2']").fill(address2);

        }
        //city input
        page.locator("//input[@id='address-ui-widgets-enterAddressCity']").fill(city);
        //state input
        page.getByLabel("State").nth(2).selectOption(state);


        //zipcode input
        page.locator("//input[@id='address-ui-widgets-enterAddressPostalCode']").fill(zip);

        page.getByRole(AriaRole.DIALOG, new Page.GetByRoleOptions().setName("Enter a new shipping address")).getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Use this address")).click();

    }


    public void saveAddress() {
        //click on save address for form
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Use this address")).click();
        page.keyboard().press("Enter");


    }
    public void clickOnAddCard() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Add a credit or debit card")).click();
    }


    public void fillCard(String number, String name, String month, String year, String cvv) {
        //switch to iframe
        FrameLocator iFrame = page.frameLocator(".apx-secure-iframe");
        //card number input
        iFrame.locator("(//a[normalize-space()='Add a credit or debit card'])[1]").fill(number);
        //name input
        iFrame.locator("//input[@id='pp-eNSXve-19']").fill(name);
        //exp month
        //create xPath by reducing the month by one for selector
        iFrame.locator("//span[@id='pp-WPP3M5-23']//span[@role='button']").click();
        //handle drop down
        String newMonth = String.valueOf(Integer.parseInt(month) - 1);
        String xPathMonth = String.format("//a[@id='pp-WPP3M5-20_%s']",newMonth);
        iFrame.locator(xPathMonth).click();
        //exp year
        //create xpath locator by calculating the year and table
        //0 = 2023, 1 = 2024, 2 = 2025... Math: year - 2023
        iFrame.locator("//span[@id='pp-WPP3M5-24']//span[@role='button']").click();
        //handle drop down
        String newYear = String.valueOf(Integer.parseInt(year) - 2023);
        String xPathYear = String.format("//a[@id='pp-WPP3M5-22_%s']",newYear);
        iFrame.locator(xPathYear).click();

        //cvv input
        iFrame.locator("//input[@id='pp-WPP3M5-26']").fill(cvv);

        //uncheck set as default and assert
        Locator defaultCheckBox = iFrame.locator("//input[@id='pp-WPP3M5-30']");
        defaultCheckBox.uncheck();
        PlaywrightAssertions.assertThat(defaultCheckBox).not().isChecked();
    }


    public void addCard() {
        page.frameLocator(".apx-secure-iframe").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Add your card")).click();
        //switch to iframe
        Locator iFrame = page.locator(".apx-secure-iframe");
        //click on add card in iframe
        iFrame.locator("//input[@name='ppw-widgetEvent:AddCreditCardEvent']").click();
        //click on use this payment method
        page.locator("//span[@id='pp-0T4pvD-96-announce']").click();
    }
}
