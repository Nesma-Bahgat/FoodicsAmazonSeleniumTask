
import java.time.Duration;
import java.util.ArrayList;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected static ArrayList<String> selectedNames;
    protected static int totalAmount = 0;
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10000));
        actions = new Actions(driver);
    }
     
    public void clickElementByJS(WebElement elementLocator){
           ((JavascriptExecutor) driver).executeScript(
        "arguments[0].click();", elementLocator);  
    }

    public void scrollToTheTopOfThePage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
    }
    
    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    public void clickElementByXPath(String xpath){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.evaluate(\"arguments[0]\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();", xpath);
        

    }

}
