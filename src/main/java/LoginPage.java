
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    String passwordValue = "Foodics@Test123";
    String mobileNumberValue = "01146369338";
    
    @FindBy(id = "ap_email")
    WebElement mobileNumber;
    
    @FindBy(id = "ap_password")
    WebElement password;
    
    @FindBy(id = "signInSubmit")
    WebElement loginBtn;
    
    @FindBy(id = "continue")
    WebElement continueBtn;
    
    
    public LoginPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10000));
    }
    
    public void loginToAmazon(){
        wait.until(ExpectedConditions.visibilityOf(mobileNumber)).click();
        mobileNumber.sendKeys(mobileNumberValue);
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
        wait.until(ExpectedConditions.visibilityOf(password)).click();
        password.sendKeys(passwordValue);
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        System.out.println("You're logged in!");
    }

}
