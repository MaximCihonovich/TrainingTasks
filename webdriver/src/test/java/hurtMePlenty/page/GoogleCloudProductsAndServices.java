package hurtMePlenty.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudProductsAndServices {

    private WebDriver driver;

    @FindBy(xpath = "//a[@class='cloud-button cloud-button--secondary' and @track-name='seePricing']")
    private WebElement seePricingButton;

    public GoogleCloudProductsAndServices(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void waitForPageToLoad () {
        new WebDriverWait(driver,4)
                .until(ExpectedConditions.elementToBeClickable(seePricingButton));
    }

    public GoogleCloudPricing pushSeePricingButton () {
        seePricingButton.click();
        return new GoogleCloudPricing(driver);
    }
}
