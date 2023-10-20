import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;
import java.util.*;

public class Example {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                    .setStorageStatePath(Paths.get("auth.json")));
            Page page = context.newPage();
            page.navigate("https://www.amazon.com/cart");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed to checkout")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Change shipping address")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Add a new address")).click();

            page.getByLabel("Phone number").fill("50482738274");

            page.getByPlaceholder("Street address or P.O. Box").fill("230 circle west dr");

            page.getByLabel("City").fill("westwego");


            page.getByLabel("ZIP Code").fill("70094");
            page.getByRole(AriaRole.DIALOG, new Page.GetByRoleOptions().setName("Enter a new shipping address")).getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Use this address")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Add a credit or debit card")).click();
            page.frameLocator(".apx-secure-iframe").getByLabel("Card number").click();
            page.frameLocator(".apx-secure-iframe").getByLabel("Card number").fill("3464 353454 36434");
            page.frameLocator(".apx-secure-iframe").getByLabel("Name on card").click();
            page.frameLocator(".apx-secure-iframe").getByLabel("Name on card").fill("mitchell nguyen");
            page.frameLocator(".apx-secure-iframe").locator("#pp-slnmWZ-23 span").nth(1).click();
            page.frameLocator(".apx-secure-iframe").locator("#pp-slnmWZ-20_6").click();
            page.frameLocator(".apx-secure-iframe").locator("#pp-slnmWZ-24 span").nth(1).click();
            page.frameLocator(".apx-secure-iframe").locator("#pp-slnmWZ-22_4").click();
            page.frameLocator(".apx-secure-iframe").locator("#pp-slnmWZ-26").click();
            page.frameLocator(".apx-secure-iframe").locator("#pp-slnmWZ-26").fill("311");
            page.frameLocator(".apx-secure-iframe").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Add your card")).click();
        }
    }
}