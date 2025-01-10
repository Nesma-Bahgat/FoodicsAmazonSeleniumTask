
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CartPage extends BasePage {

    @FindBy(id = "sc-subtotal-amount-activecart")
    WebElement totalPrice;

    @FindBy(xpath = "//div[contains(@class, 'sc-item-content-group') and .//h4//span]")
    List<WebElement> productsList;

    @FindBy(xpath = "//input[@data-feature-id='proceed-to-checkout-action']")
    WebElement proccedToBuyButton;

    List<String> cartProductNames;

    public CartPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10000));
    }

    public void getTotalAmount() {
        String priceText = totalPrice.getText().replaceAll("[^\\d]", "");
        BasePage.totalAmount = Integer.parseInt(priceText);
    }

    public void CompareNames() {
        cartProductNames = new ArrayList();

        wait.until(ExpectedConditions.visibilityOfAllElements(productsList));

        for (WebElement div : productsList) {
            scrollToElement(div);
            WebElement name = div.findElement(By.xpath(".//h4//span"));

            String productName = name.getText();

            if (productName.length() > 150) {
                productName = productName.substring(0, 150);
            }

            cartProductNames.add(productName);
        }

        Collections.sort(cartProductNames);
        System.out.println("Cart products 2:" + cartProductNames);
        Collections.sort(BasePage.selectedNames);
        System.out.println("Selected products 2:" + selectedNames);

        for (int i = 0; BasePage.selectedNames.size() > i; i++) {
            String cartItemName = cartProductNames.get(i);

            System.out.println("productName in cart: " + cartItemName);
            System.out.println("productName in list: " + BasePage.selectedNames.get(i));

            Assert.assertTrue(BasePage.selectedNames.get(i).contains(cartItemName));
        }
    }

    public void clickOnProceedToBuyButton() {
        scrollToTheTopOfThePage();
       wait.until(ExpectedConditions.elementToBeClickable(proccedToBuyButton)).click();
    }

}
