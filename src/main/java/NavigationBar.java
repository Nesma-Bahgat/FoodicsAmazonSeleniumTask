
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationBar extends BasePage {

    @FindBy(id = "nav-cart")
    WebElement cartButton;

    public NavigationBar(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10000));
    }

    public void goToCartPage() {
        scrollToTheTopOfThePage();
        wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();
    }

}
