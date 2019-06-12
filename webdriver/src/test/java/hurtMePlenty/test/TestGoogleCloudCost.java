package hurtMePlenty.test;

import hurtMePlenty.page.GoogleCloudMainPage;
import hurtMePlenty.page.GoogleCloudPlatformPricingCalculator;
import hurtMePlenty.page.GoogleCloudPricing;
import hurtMePlenty.page.GoogleCloudProductsAndServices;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;

public class TestGoogleCloudCost {

    private WebDriver driver = new FirefoxDriver();
    private List<WebElement> estimateFieldsList;
    private String instanceType = "n1-standard-8";
    private String availableLocalSSDSpace = "2x375";
    private String region = "Frankfurt";
    private String commitmentTerm = "1 Year";

    private boolean compareResultWithInputData (List<WebElement> resultsList, String resultType, String comparedData) {
        for (WebElement element : resultsList) {
            if (element.getText().contains(resultType)) {
                return element.getText().contains(comparedData);
            }
        }
        return false;
    }

    @BeforeClass
    public void generateEstimate () {
        driver.manage().window().maximize();
        GoogleCloudMainPage mainPage = new GoogleCloudMainPage(driver).openPage();
        GoogleCloudProductsAndServices productsPage = mainPage.pushSeeProductsButton();
        productsPage.waitForPageToLoad();
        GoogleCloudPricing pricePage = productsPage.pushSeePricingButton();
        pricePage.waitForPageToLoad();
        GoogleCloudPlatformPricingCalculator calculator = pricePage.pushPricingElementCalculators();
        calculator.waitForPageToLoad();
        calculator.generateEstimate(instanceType,availableLocalSSDSpace,region,commitmentTerm);
        estimateFieldsList = calculator.readEstimate();
    }

    @Test
    public void testVirtualMachineClass () {
        Assert.assertTrue(compareResultWithInputData(estimateFieldsList,"VM class:","regular"));
    }

    @Test
    public void testInstanceType () {
        Assert.assertTrue(compareResultWithInputData(estimateFieldsList,"Instance type:",instanceType));
    }

    @Test
    public void testRegion () {
        Assert.assertTrue(compareResultWithInputData(estimateFieldsList,"Region:",region));
    }

    @Test
    public void testLocalSSD () {
        Assert.assertTrue(compareResultWithInputData(estimateFieldsList,"Total available local SSD space",availableLocalSSDSpace));
    }

    @Test
    public void testCommitmentTerm () {
        Assert.assertTrue(compareResultWithInputData(estimateFieldsList,"Commitment term:",commitmentTerm));
    }

    @Test
    public void testEstimateCost () {
        Assert.assertTrue(compareResultWithInputData(estimateFieldsList,"Estimated Component Cost:","1,187.77"));
    }

    @AfterClass
    public void teardownBrowser () {
        driver.quit();
    }
}
