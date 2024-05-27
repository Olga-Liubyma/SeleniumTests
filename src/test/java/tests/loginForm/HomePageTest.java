package tests.loginForm;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class HomePageTest extends BaseTest {
    @Test(priority = 1)
    public void checkLoginFormItemsArePresent() {
        homePage
                .verifyEmailInputIsPresent()
                .verifyPasswordInputIsPresent()
                .enterEmail("example@example.com")
                .enterPassword("password123")
                .verifyloginButtonIsPresent();
    }

   @Test(priority = 2)
    public void checkListGroupItems() {
        homePage
                .verifyListGroupItemsCount(3)
                .verifyListItemTitle("List Item 2")
                .verifySecondListItemBadgeValue("6");
    }

    @Test(priority = 3)
    public void checkDropDownMenu() {
        homePage
                .verifySelectedOption("Option 1")
                .clickDropDownMenu()
                .selectOptionByNumber(3)
                .verifySelectedOption("Option 3");
    }

    @Test(priority = 4)
    public void checkButtons() {
        homePage
                .verifyFirstButtonIsEnabled()
                .verifySecondButtonIsDisabled();
    }

    @Test(priority = 5)
    public void checkClickButtonAndSuccessMassage() {
        homePage
                .verifySuccessMessageAndAssertButtonIsDisabled();
    }

    @Test(priority = 6)
    public void checkCellValue() {
        homePage
                .verifyGridCellValue(2, 2, "Ventosanzap");
    }
}
