package bringItOn.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;


public class PastebinMainPage {

    private WebDriver driver;

    @FindBy (xpath = "//*[@id='paste_code']")
    private WebElement newPasteArea;

    @FindBy(xpath = "//div[@class='form_left' and text()='\n" +
            "\t\t\t\t\tSyntax Highlighting:\n" +
            "\t\t\t\t']/following::span[@class='select2-selection select2-selection--single']")
    private WebElement pasteSettingSyntaxHighlightingCombobox;

    @FindBy(xpath = "//div[@class='form_left' and text()='\n" +
            "\t\t\t\t\tPaste Expiration:\n" +
            "\t\t\t\t']/following::span[@class='select2-selection select2-selection--single']")
    private WebElement pasteSettingExpirationCombobox;

    @FindBy (xpath = "//input[@type='text' and @name='paste_name']")
    private WebElement pasteSettingNameString;

    @FindBy(xpath = "//*[@id='submit' and @value='Create New Paste']")
    private WebElement createNewPasteButton;

    public PastebinMainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public PastebinMainPage openPage() {
        driver.get("https://pastebin.com/");
        new WebDriverWait(driver,10)
                .until(ExpectedConditions.elementToBeClickable(createNewPasteButton));
        return this;
    }

    public PastebinPageWithNewPaste writeNewPaste (String pasteTitle, String syntaxHighlighting, String ... pasteText) {
        for (String pasteString : pasteText) {
            newPasteArea.sendKeys(pasteString);
            new Actions(driver).sendKeys(Keys.ENTER).perform();
        }
        pasteSettingSyntaxHighlightingCombobox.click();
        List<WebElement> syntaxHighlightingTypes = driver.findElements(By.xpath("//li[@class='select2-results__option' and @aria-selected='false']"));
        for (WebElement syntaxType : syntaxHighlightingTypes) {
            if (syntaxType.getText().equals(syntaxHighlighting)) {
                syntaxType.click();
                break;
            }
        }
        pasteSettingExpirationCombobox.click();
        new Actions(driver).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        pasteSettingNameString.sendKeys(pasteTitle);
        createNewPasteButton.click();
        return new PastebinPageWithNewPaste(driver);
    }
}
