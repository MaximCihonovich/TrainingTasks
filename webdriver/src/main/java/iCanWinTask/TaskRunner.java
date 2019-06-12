package iCanWinTask;

import iCanWinTask.page.PastebinMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TaskRunner {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        new PastebinMainPage(driver)
                .openPage()
                .writeNewPaste("Hello from WebDriver", "helloweb");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
