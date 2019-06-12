package hurtMePlenty.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudPricing {

    private WebDriver driver;

    @FindBy(xpath = "//a[@track-name='pricingNav/calculators']")
    private WebElement sidePricingMenuElement;

    public GoogleCloudPricing(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void waitForPageToLoad () {
        new WebDriverWait(driver,4)
                .until(ExpectedConditions.visibilityOf(sidePricingMenuElement));
    }

    public GoogleCloudPlatformPricingCalculator pushPricingElementCalculators () {
        sidePricingMenuElement.click();
        return new GoogleCloudPlatformPricingCalculator(driver);
    }
}
