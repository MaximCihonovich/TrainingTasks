package hurtMePlenty.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class GoogleCloudPlatformPricingCalculator {

    private WebDriver driver;

    @FindBy(xpath = "//iframe[@id='idIframe']")
    private WebElement calculatorFrame;

    @FindBy(xpath = "//input[@placeholder='Search for a product you are interested in.']")
    private WebElement productsInputField;

    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.quantity']")
    private WebElement numberOfInstancesField;

    @FindBy(xpath = "//*[@placeholder='Instance type']")
    private WebElement machineTypeCombobox;

    @FindBy(xpath = "//*[@id='select_container_75']/descendant::md-option")
    private List<WebElement> machineTypeOptionsList;

    @FindBy(xpath = "//*[@aria-label='Add GPUs' and @role='checkbox']")
    private WebElement addGPUsCheckbox;

    @FindBy(xpath = "//*[@placeholder='Number of GPUs']")
    private WebElement numberOfGPUsCombobox;

    @FindBy(xpath = "//*[@ng-disabled='item.value != 0 && item.value < listingCtrl.minGPU' and @value='4']")
    private WebElement numberOfGPUsOption;

    @FindBy(xpath = "//*[@placeholder='GPU type']")
    private WebElement typeOfGPUsCombobox;

    @FindBy(xpath = "//*[@value='NVIDIA_TESLA_V100']")
    private WebElement typeOfGPUsOption;

    @FindBy(xpath = "//*[@placeholder='Local SSD']")
    private WebElement localSSDCombobox;

    @FindBy(xpath = "//*[@id='select_container_77']/descendant::md-option")
    private List<WebElement> localSSDOptionsList;

    @FindBy(xpath = "//*[@placeholder='Datacenter location']")
    private WebElement datacenterLocationCombobox;

    @FindBy(xpath = "//*[@id='select_container_79']/descendant::md-option")
    private List<WebElement> datacenterLocationOptionsList;

    @FindBy(xpath = "//*[@placeholder='Committed usage']")
    private WebElement commitedUsageCombobox;

    @FindBy(xpath = "//*[@id='select_container_84']/descendant::md-option")
    private List<WebElement> commitedUsageOptionsList;

    @FindBy(xpath = "//button[@ng-disabled='ComputeEngineForm.$invalid || !listingCtrl.isGceAvailabele']")
    private WebElement addToEstimateButton;

    @FindBy(xpath = "//*[@id='compute']/descendant::md-list-item")
    private List<WebElement> estimateFieldsList;

    public GoogleCloudPlatformPricingCalculator(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    private void optionsListElementClick (List<WebElement> optionsList, String desiredElement) {
        for (WebElement element : optionsList) {
            if (element.getText().contains(desiredElement)) {
                element.click();
                break;
            }
        }
    }

    public void waitForPageToLoad () {
        new WebDriverWait(driver,6)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(calculatorFrame));
    }

    public GoogleCloudPlatformPricingCalculator generateEstimate
            (String instanceType, String availableLocalSSDSpace, String region, String commitmentTerm) {
        productsInputField.sendKeys("Compute Engine");
        new Actions(driver).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        numberOfInstancesField.sendKeys("4");
        new Actions(driver).click().sendKeys(Keys.PAGE_DOWN).perform();
        new WebDriverWait(driver,1).until(ExpectedConditions.visibilityOf(machineTypeCombobox));
        machineTypeCombobox.click();
        optionsListElementClick(machineTypeOptionsList,instanceType);
        new WebDriverWait(driver,1).until(ExpectedConditions.elementToBeClickable(addGPUsCheckbox));
        addGPUsCheckbox.click();
        new WebDriverWait(driver,1).until(ExpectedConditions.visibilityOfAllElements(numberOfGPUsCombobox,typeOfGPUsCombobox));
        numberOfGPUsCombobox.click();
        numberOfGPUsOption.click();
        typeOfGPUsCombobox.click();
        typeOfGPUsOption.click();
        ((JavascriptExecutor)driver).executeScript("scroll(0,250)");
        new WebDriverWait(driver,1).until(ExpectedConditions.visibilityOf(localSSDCombobox));
        localSSDCombobox.click();
        optionsListElementClick(localSSDOptionsList,availableLocalSSDSpace);
        datacenterLocationCombobox.click();
        optionsListElementClick(datacenterLocationOptionsList,region);
        commitedUsageCombobox.click();
        optionsListElementClick(commitedUsageOptionsList,commitmentTerm);
        new WebDriverWait(driver,1).until(ExpectedConditions.elementToBeClickable(addToEstimateButton));
        addToEstimateButton.click();
        return this;
    }

    public List<WebElement> readEstimate () {
        new WebDriverWait(driver,1).until(ExpectedConditions.visibilityOfAllElements(estimateFieldsList));
        return estimateFieldsList;
    }
}
