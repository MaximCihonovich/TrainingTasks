package bringItOn.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PastebinPageWithNewPaste {

    private WebDriver driver;

    @FindBy(xpath = "//textarea[@id='paste_code']")
    private WebElement rawPasteDataArea;

    @FindBy(xpath = "//a[@class='buttonsm' and @style='margin:0']")
    private WebElement syntaxHighlighting;

    PastebinPageWithNewPaste(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void waitForPageToLoad () {
        new WebDriverWait(driver,3)
                .until(ExpectedConditions.visibilityOfAllElements(rawPasteDataArea,syntaxHighlighting));
    }

    public String readRawPasteDataArea () {
        return rawPasteDataArea.getText();
    }

    public String checkSyntaxHighlighting () {
        return syntaxHighlighting.getText();
    }
}
