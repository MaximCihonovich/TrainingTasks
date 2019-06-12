package hardcore.test;

import hardcore.page.GoogleCloudCalculatorWithEstimatedCostMailed;
import hardcore.page.TemporaryMailboxGenerator;
import hurtMePlenty.page.GoogleCloudMainPage;
import hurtMePlenty.page.GoogleCloudPricing;
import hurtMePlenty.page.GoogleCloudProductsAndServices;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;


public class TestMailedGoogleCloudCost {

    private WebDriver driver = new FirefoxDriver();
    private String estimatedCost, receivedByMailEstimatedCost;

    @BeforeMethod
    public void sendGeneratedEstimateByMail () {
        ArrayList<String> newBrowserTabHandle;
        String email, calculatorHandle;
        String instanceType = "n1-standard-8";
        String availableLocalSSDSpace = "2x375";
        String region = "Frankfurt";
        String commitmentTerm = "1 Year";
        driver.manage().window().maximize();
        GoogleCloudMainPage mainPage = new GoogleCloudMainPage(driver).openPage();
        GoogleCloudProductsAndServices productsPage = mainPage.pushSeeProductsButton();
        productsPage.waitForPageToLoad();
        GoogleCloudPricing pricePage = productsPage.pushSeePricingButton();
        pricePage.waitForPageToLoad();
        pricePage.pushPricingElementCalculators();
        GoogleCloudCalculatorWithEstimatedCostMailed calculator = new GoogleCloudCalculatorWithEstimatedCostMailed(driver);
        calculator.waitForPageToLoad();
        calculator.generateEstimate(instanceType,availableLocalSSDSpace,region,commitmentTerm);
        estimatedCost = calculator.readTotalEstimateCost();
        calculatorHandle = driver.getWindowHandle();
        ((JavascriptExecutor)driver).executeScript("window.open('https://10minutemail.com','_blank');");
        newBrowserTabHandle = new ArrayList<String>(driver.getWindowHandles());
        newBrowserTabHandle.remove(calculatorHandle);
        driver.switchTo().window(newBrowserTabHandle.get(0));
        TemporaryMailboxGenerator mailBox = new TemporaryMailboxGenerator(driver).waitForPageToLoad();
        email = mailBox.readTemporaryEmailAddress();
        driver.switchTo().window(calculatorHandle);
        calculator.sendEstimateByEmail(email);
        driver.close();
        driver.switchTo().window(newBrowserTabHandle.get(0));
        receivedByMailEstimatedCost = mailBox.readReceivedByMailEstimatedCost();
    }

    @Test
    public void testMailedCost () {
        Assert.assertTrue(estimatedCost.contains(receivedByMailEstimatedCost));
    }

    @AfterMethod
    public void teardownBrowser () {
        driver.quit();
    }
}
