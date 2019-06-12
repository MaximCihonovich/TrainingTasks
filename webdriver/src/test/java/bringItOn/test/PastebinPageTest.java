package bringItOn.test;

import bringItOn.page.PastebinPageWithNewPaste;
import bringItOn.page.PastebinMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PastebinPageTest {

    private WebDriver driver = new ChromeDriver();
    private String [] newPasteStrings = new String[]{"git config --global user.name  \"New Sheriff in Town\"",
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")",
            "git push origin master --force"};
    private String syntaxHighlightingType = "Bash";
    private String pasteTitle = "how to gain dominance among developers";
    private PastebinPageWithNewPaste pageWithNewPaste;

    private String readPastedText () {
        StringBuilder pastedText = new StringBuilder();
        for (byte i = 0; i < newPasteStrings.length; ++i) {
            if (i != newPasteStrings.length-1 ) {
                pastedText.append(newPasteStrings[i]);
                pastedText.append('\n');
            } else { pastedText.append(newPasteStrings[i]);}
        }
        return pastedText.toString();
    }

    @BeforeClass
    public void writeNewPaste () {
        PastebinMainPage newPaste = new PastebinMainPage(driver).openPage();
        pageWithNewPaste = newPaste.writeNewPaste(pasteTitle,syntaxHighlightingType,newPasteStrings);
        pageWithNewPaste.waitForPageToLoad();
    }

    @Test
    public void testPageTitle () {
        Assert.assertTrue(driver.getTitle().contains(pasteTitle));
        }

    @Test
    public void testSyntaxHighlight () {
        Assert.assertEquals(pageWithNewPaste.checkSyntaxHighlighting(),syntaxHighlightingType);
    }

    @Test
    public  void testPastRawData () {
        Assert.assertEquals(pageWithNewPaste.readRawPasteDataArea(),readPastedText());
    }

    @AfterClass
    public void teardownBrowser () {
        driver.quit();
    }
}
