
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    @FindBy(id = "nav-link-accountList")
    WebElement accountSectionInNavBar;

    @FindBy(id = "nav-flyout-ya-signin")
    WebElement signInBtn;

    @FindBy(id = "nav-hamburger-menu")
    WebElement allMenuButton;

    
    public HomePage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10000));
        actions = new Actions(driver);
    }

    public void hooverAccountSectionInNavBar() {
        wait.until(ExpectedConditions.visibilityOf(accountSectionInNavBar));
        actions.moveToElement(accountSectionInNavBar).perform();
    }

    public void clickOnSignInLink() {
        wait.until(ExpectedConditions.visibilityOf(signInBtn)).click();
    }

    public void clickOnAllMenuButton() {
        wait.until(ExpectedConditions.elementToBeClickable(allMenuButton)).click();
    }

   }
