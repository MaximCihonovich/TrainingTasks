package hurtMePlenty.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class GoogleCloudMainPage {

    private WebDriver driver;

    @FindBy(xpath = "//a[@class='cloud-button cloud-button--secondary' and @track-name='seeProducts']")
    private WebElement seeProductsButton;

    public GoogleCloudMainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public GoogleCloudMainPage openPage() {
        driver.get("https://cloud.google.com/");
        new WebDriverWait(driver,10)
                .until(ExpectedConditions.elementToBeClickable(seeProductsButton));
        return this;
    }

    public GoogleCloudProductsAndServices pushSeeProductsButton () {
        seeProductsButton.click();
        return new GoogleCloudProductsAndServices(driver);
    }
}
