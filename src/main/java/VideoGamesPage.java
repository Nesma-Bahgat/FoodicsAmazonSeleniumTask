
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VideoGamesPage extends BasePage {

    @FindBy(xpath = "//label[@for='apb-browse-refinements-checkbox_0']/i")
    WebElement freeShippingCheckBox;

    @FindBy(id = "s-result-sort-select")
    WebElement sortByDropDownList;

    @FindBy(xpath = "//div[contains(@class, 'a-section a-spacing-small a-spacing-top-small') and .//button]")
    List<WebElement> productsListWithButtons;

    public VideoGamesPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10000));
    }

    public void selectFreeShipping() {
        try {
            freeShippingCheckBox.click();
        } catch (ElementNotInteractableException e) {
            scrollToElement(freeShippingCheckBox);
            freeShippingCheckBox.click();
        } catch (Exception e) {
            throw e;
        }
    }

    public void selectCondition(String conditionType) {
        WebElement conditionName = driver.findElement(By.xpath("//span[text()='" + conditionType + "']"));
        try {
            conditionName.click();
        } catch (ElementNotInteractableException e) {
            scrollToElement(conditionName);
            wait.until(ExpectedConditions.elementToBeClickable(conditionName)).click();
        } catch (Exception e) {
            throw e;
        }
    }

    public void sortBy(String sortValue) {
        Select select = new Select(sortByDropDownList);
        select.selectByValue(sortValue);
        WebElement outsideElement = driver.findElement(By.xpath("//body")); // Clicks on the body to dismiss the dropdown
        outsideElement.click();
    }

    public void selectProductsBelow15K() {
        BasePage.selectedNames = new ArrayList<>();
        wait.until(ExpectedConditions.visibilityOfAllElements(productsListWithButtons));
        System.out.println("List has : " + productsListWithButtons.size() + " Elements");

        for (WebElement div : productsListWithButtons) {
            scrollToElement(div);
            WebElement priceElement = div.findElement(By.xpath(".//span[@class='a-price-whole']"));
            String priceText = priceElement.getText().replaceAll("[^\\d]", "");
            System.out.println("Price: " + priceText);

            int price = Integer.parseInt(priceText);

            try {
                if (price < 15000) {
                    WebElement cartBtn = div.findElement(By.xpath(".//button"));
                    clickElementByJS(cartBtn);

                    System.out.println("Price: " + priceText + " selected");

                    WebElement name = div.findElement(By.xpath(".//h2//span"));

                    String productName = name.getText();
                    BasePage.selectedNames.add(productName);
                }
            } catch (Exception e) {
                // Handle cases where price parsing fails or "Add to Cart" is not available
                System.out.println("Skipping product due to an error: " + e.getMessage());
            }
        }
        System.out.println("Selected Items:" + BasePage.selectedNames);
    }
}
