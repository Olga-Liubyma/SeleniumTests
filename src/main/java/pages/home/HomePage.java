package pages.home;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import java.io.File;
import java.util.List;
import static constants.Constants.URLs.HOME_PAGE_URL;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage openHomePage() {
        File htmlFile = new File(HOME_PAGE_URL);
        open(htmlFile.toURI().toString());
        return this;
    }

    private final By emailInput = By.id("inputEmail");
    private final By passwordInput = By.id("inputPassword");
    private final By loginButton = By.cssSelector(".btn.btn-lg.btn-primary.btn-block");
    private final By badge = By.xpath("//span[contains(@class,\"badge-primary\") and text()=\"6\"]");
    private final By itemsList = By.xpath("//*[contains (@class, 'list-group-item')]");
    private final By dropDownMenuButton = By.id("dropdownMenuButton");
    private final By firstButton = By.xpath("//div[@id=\"test-4-div\"]//button[@class=\"btn btn-lg btn-primary\"]");
    private final By secondButton = By.xpath("//div[@id=\"test-4-div\"]//button[@class=\"btn btn-lg btn-secondary\"]");
    private final By test5button = By.id("test5-button");
    private final By successMessage = By.id("test5-alert");
    private By getCell(int rowNumber, int columnNumber) {
        return By.xpath(String.format("//table[@class='table table-bordered table-dark']/tbody/tr[%d]/td[%d]", rowNumber, columnNumber));
    }

    // region Actions
    public HomePage enterEmail(String text) {
        driver.findElement(emailInput).sendKeys(text);
        return this;
    }

    public HomePage enterPassword(String text) {
        driver.findElement(passwordInput).sendKeys(text);
        return this;
    }

    public HomePage clickDropDownMenu() {
        driver.findElement(dropDownMenuButton).click();
        return this;
    }

    public HomePage clickButton5() {
        driver.findElement(test5button).click();
        return this;
    }

    public HomePage selectOptionByNumber(int number) {
        String xpathExpression = String.format("//a[@class='dropdown-item' and contains(text(), '%d')]", number);
        driver.findElement(By.xpath(xpathExpression)).click();
        return this;
    }

    public String getGridCellValue(int rowNumber, int columnNumber) {
        rowNumber += 1;
        columnNumber += 1;
        By cell = getCell(rowNumber, columnNumber);
        return driver.findElement(cell).getText().trim();
    }
    // endregion

    // region Verifications
    public HomePage verifyEmailInputIsPresent() {
        driver.findElement(emailInput).isDisplayed();
        return this;
    }

    public HomePage verifyPasswordInputIsPresent() {
        driver.findElement(passwordInput).isDisplayed();
        return this;
    }

    public HomePage verifyloginButtonIsPresent() {
        driver.findElement(loginButton).isDisplayed();
        return this;
    }

    public HomePage verifyListGroupItemsCount(int expectedCount) {
        List<WebElement> listItems = driver.findElements(itemsList);
        int actualCount = listItems.size();
        Assert.assertEquals(actualCount, expectedCount, "The number of items in the list group is not as expected.");
        return this;
    }

    public HomePage verifyListItemTitle(String expectedValue) {
        WebElement secondItem = driver.findElements(itemsList).get(1);
        String actualValue = secondItem.getText().trim().substring(0, secondItem.getText().trim().length() - 2);
        Assert.assertEquals(actualValue, expectedValue, "The value of the second list item is not as expected.");
        return this;
    }

    public HomePage verifySecondListItemBadgeValue(String expectedBadgeValue) {
        WebElement secondItem = driver.findElements(itemsList).get(1);
        String actualBadgeValue = driver.findElement(badge).getText();
        Assert.assertEquals(actualBadgeValue, expectedBadgeValue, "The badge value of the second list item is not as expected.");
        return this;
    }

    public HomePage verifySelectedOption(String expectedResult) {
        Assert.assertEquals(driver.findElement(dropDownMenuButton).getText().trim(), expectedResult.trim(), "The selected option should match the expected option");
        return this;
    }

    public HomePage verifyFirstButtonIsEnabled() {
        Assert.assertTrue(driver.findElement(firstButton).isEnabled(), "The state of the button does not match the expected state");
        return this;
    }

    public HomePage verifySecondButtonIsDisabled() {
        Assert.assertFalse(driver.findElement(secondButton).isEnabled(), "The state of the button does not match the expected state");
        return this;
    }

    public HomePage verifySuccessMessageAndAssertButtonIsDisabled() {
        waitElementIsVisible(driver.findElement(test5button));
        clickButton5();
        waitElementIsVisible(driver.findElement(successMessage));
        Assert.assertTrue(driver.findElement(successMessage).isDisplayed(), "Success message is not displayed.");
        Assert.assertFalse(driver.findElement(test5button).isEnabled(), "The button should be disabled after clicking it.");
        return this;
    }

    public HomePage verifyGridCellValue(int rowNumber, int columnNumber, String expectedValue) {
        String actualValue = getGridCellValue(rowNumber, columnNumber);
        Assert.assertEquals(actualValue, expectedValue, "The actual value does not match the expected value.");
        return this;
    }
    // endregion
}
