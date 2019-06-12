package hardcore.page;

import hurtMePlenty.page.GoogleCloudPlatformPricingCalculator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudCalculatorWithEstimatedCostMailed extends GoogleCloudPlatformPricingCalculator {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id='resultBlock']/md-card/md-card-content/div/div/div/h2/b")
    private WebElement totalEstimatedCostLabel;

    @FindBy(xpath = "//button[@id='email_quote']")
    private WebElement emailEstimateButton;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailFieldOfFormEmailYourEstimate;

    @FindBy(xpath = "//*[@aria-label='Send Email' and @type='button']")
    private WebElement sendEmailButtonOfFormEmailYourEstimate;

    public GoogleCloudCalculatorWithEstimatedCostMailed(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public String readTotalEstimateCost () {
        return totalEstimatedCostLabel.getText();
    }

    public void sendEstimateByEmail (String email) {
        new Actions(driver).click().sendKeys(Keys.PAGE_DOWN).perform();
        new WebDriverWait(driver,2)
                .until(ExpectedConditions.visibilityOf(emailEstimateButton));
        emailEstimateButton.click();
        new Actions(driver).click().sendKeys(Keys.PAGE_DOWN).perform();
        new WebDriverWait(driver,1)
                .until(ExpectedConditions.visibilityOf(emailFieldOfFormEmailYourEstimate));
        emailFieldOfFormEmailYourEstimate.sendKeys(email);
        new WebDriverWait(driver,3)
                .until(ExpectedConditions.elementToBeClickable(sendEmailButtonOfFormEmailYourEstimate));
        sendEmailButtonOfFormEmailYourEstimate.click();
    }
}
