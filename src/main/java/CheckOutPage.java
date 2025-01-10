
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CheckOutPage extends BasePage {

    @FindBy(xpath = "//span[contains(text(),'Buy Now, Pay Later with Valu')][1]")
    List<WebElement> payByValuRadiobutton;

    @FindBy(xpath = "//div[@class='order-summary-grid' and .//span[text()='Order total']]")
    WebElement orderTotalSection;

    @FindBy(xpath = "//div[@class='order-summary-grid' and .//span[text()='Shipping & handling:']]")
    WebElement shippingFeesSection;

    @FindBy(xpath = "//div[contains(text(),'Free Delivery')]")
    WebElement freeDeliverySection;

    public CheckOutPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10000));
    }

    public void selectPayByValuPaymentMethod() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(payByValuRadiobutton.get(1))).click();
    }

    public int getShippingfees() throws InterruptedException {
       Thread.sleep(5000);
               String shippingFeesText = shippingFeesSection.findElement(By.xpath("//span[@class='aok-nowrap a-nowrap']")).getText();
        if (shippingFeesText.contains("--")) {
            return 0;
        }

        return Integer.parseInt(shippingFeesText);
    }

    public int getFreeDeliverValue() {
        String freeDeliveryText = freeDeliverySection.findElement(By.xpath("//span[@class='aok-nowrap a-nowrap']")).getText();
        if (freeDeliveryText.contains("--")) {
            return 0;
        }

        return Integer.parseInt(freeDeliveryText);
    }

    public int getOrderTotalValue() {
        WebElement orderTotalElement = orderTotalSection.findElement(By.xpath(".//div[@class='order-summary-line-definition']"));
        String orderTotalText = orderTotalElement.getText().replaceAll("[^\\d]", "");
        System.out.println("Current Order Total: "+ orderTotalText);
        return Integer.parseInt(orderTotalText);
    }

    public void checkOrderTotalValue() throws InterruptedException {
        int orderTotalValue = BasePage.totalAmount + getShippingfees() - getFreeDeliverValue();
        System.out.println("Calculated Order Total:" + orderTotalValue);
        Assert.assertEquals(orderTotalValue, getOrderTotalValue());
    }

}
