
import org.testng.annotations.Test;

public class TestClass extends BaseTest {

    int videoGamesItemID = 16;
    String allVideoGames = "All Video Games", sortByHighToLow = "price-desc-rank", conditionTypeNew = "New";

    @Test(priority = 1)
    public void loginToAmazon() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        homePage.hooverAccountSectionInNavBar();
        homePage.clickOnSignInLink();
        loginPage.loginToAmazon();
    }

    @Test(priority = 2)
    public void navigateToVideoGamesPage() {
        HomePage homePage = new HomePage(driver);
        MainMenu mainMenu = new MainMenu(driver);
        homePage.clickOnAllMenuButton();
        mainMenu.clickOnSeeAllBtn();
        mainMenu.selectMenuItem(videoGamesItemID);
        mainMenu.selectSubMenuItem(allVideoGames);
    }

    @Test(priority = 3)
    public void selectvideoItems() throws InterruptedException {
        VideoGamesPage videoGamesPage = new VideoGamesPage(driver);
        NavigationBar navigationBar = new NavigationBar(driver);
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        CartPage cartPage = new CartPage(driver);
        videoGamesPage.selectFreeShipping();
        videoGamesPage.selectCondition(conditionTypeNew);
        videoGamesPage.sortBy(sortByHighToLow);
        videoGamesPage.selectProductsBelow15K();
        navigationBar.goToCartPage();
        cartPage.CompareNames();
        cartPage.getTotalAmount();
        cartPage.clickOnProceedToBuyButton();
        checkOutPage.selectPayByValuPaymentMethod();
        checkOutPage.checkOrderTotalValue();
    }
}
