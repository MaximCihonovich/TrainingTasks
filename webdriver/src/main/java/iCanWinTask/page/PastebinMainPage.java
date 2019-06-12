package iCanWinTask.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PastebinMainPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id='paste_code']")
    private WebElement newPasteField;

    @FindBy(xpath = "//span[@class='select2-selection select2-selection--single']/following::span[@class='select2-selection select2-selection--single']")
    private WebElement pasteSettingExpirationCombobox;

    @FindBy(xpath = "//input[@type='text' and @name='paste_name']")
    private WebElement pasteSettingNameString;

    @FindBys({
            @FindBy(xpath = "//*[@id='submit']"),
            @FindBy(xpath = "//*[@value='Create New Paste']")})
    private WebElement createNewPasteButton;

    public PastebinMainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PastebinMainPage openPage() {
        driver.get("https://pastebin.com/");
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(createNewPasteButton));
        return this;
    }

    public void writeNewPaste(String newPaste, String pasteName) {
        newPasteField.sendKeys(newPaste);
        pasteSettingExpirationCombobox.click();
        new Actions(driver).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        pasteSettingNameString.sendKeys(pasteName);
        createNewPasteButton.click();
    }
}
