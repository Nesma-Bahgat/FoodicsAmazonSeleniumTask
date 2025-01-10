
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainMenu extends BasePage {

    @FindBy(xpath = "//a[@class='hmenu-item hmenu-compressed-btn'][1]")
    WebElement seeAllBtn;

    public MainMenu(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10000));
    }

    public void clickOnSeeAllBtn() {
        wait.until(ExpectedConditions.visibilityOf(seeAllBtn)).click();
    }

    public void selectMenuItem(int menuItemID) {
        WebElement menuItem = driver.findElement(By.xpath("//a[@data-menu-id='" + menuItemID + "']"));
        clickElementByJS(menuItem);
    }

    public void selectSubMenuItem(String itemName) {
        WebElement menuItem = driver.findElement(By.xpath("//a[text()='" + itemName + "']"));
        clickElementByJS(menuItem);
    }

}
