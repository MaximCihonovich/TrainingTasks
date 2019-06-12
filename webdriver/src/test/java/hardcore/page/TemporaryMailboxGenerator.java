package hardcore.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TemporaryMailboxGenerator {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id='mailAddress']")
    private WebElement temporaryMailAddressLabel;

    @FindBy(xpath = "//*[@id='ui-id-1']")
    private WebElement newMailString;

    @FindBy(xpath = "//*[@id='mobilepadding']/td/table/tbody/tr[2]/td[2]/h3")
    private WebElement receivedByMailEstimatedCost;

    public TemporaryMailboxGenerator(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public TemporaryMailboxGenerator waitForPageToLoad () {
        new Actions(driver).click().sendKeys(Keys.PAGE_DOWN).perform();
        new WebDriverWait(driver,10)
                .until(ExpectedConditions.visibilityOf(temporaryMailAddressLabel));
        return this;
    }

    public String readTemporaryEmailAddress() {
        return temporaryMailAddressLabel.getAttribute("value");
    }

    public String readReceivedByMailEstimatedCost () {
        new Actions(driver).click().sendKeys(Keys.PAGE_DOWN).perform();
        new WebDriverWait(driver,50).until(ExpectedConditions.elementToBeClickable(newMailString));
        newMailString.click();
        ((JavascriptExecutor)driver).executeScript("scroll(0,450)");
        return receivedByMailEstimatedCost.getText();
    }

}
