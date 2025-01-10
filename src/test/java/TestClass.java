
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
    public void selectvideoItems() {
        VideoGamesPage videoGamesPage = new VideoGamesPage(driver);
        videoGamesPage.selectFreeShipping();
        videoGamesPage.selectCondition(conditionTypeNew);
        videoGamesPage.sortBy(sortByHighToLow);
        videoGamesPage.selectProductsBelow15K();
    }

    @Test(priority = 4)
    public void checkItemsInCartPage() throws InterruptedException {

        NavigationBar navigationBar = new NavigationBar(driver);
        CartPage cartPage = new CartPage(driver);
        navigationBar.goToCartPage();
        cartPage.CompareNames();
        cartPage.getTotalAmount();
        cartPage.clickOnProceedToBuyButton();
    }

    @Test(priority = 5)
    public void verifyCheckOutPage() {
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        checkOutPage.selectPayByValuPaymentMethod();
        checkOutPage.checkOrderTotalValue();
    }

}
