import org.apache.commons.logging.Log;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class AbstractWebTest {

    public static WebDriver chromeDriver;

    public static void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\lib\\chromedriver.exe");
        chromeDriver = new ChromeDriver();
        String handlehost;
        chromeDriver.get(Constants.STAT_SERVER_ADDRESS);
        handlehost = chromeDriver.getWindowHandle();
        chromeDriver.switchTo().window(handlehost);
        chromeDriver.switchTo().activeElement();
        chromeDriver.manage().window().maximize();
        Thread.sleep(1000);
    }

    public static void takeScreenshot(WebDriver chromeDriver, String filename) throws IOException {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy(HH.mm)");
        String FILE_PATH = "d:\\Selenium\\Chrome\\StatServer\\";
        String fileFormat = df.format(new Date()) + "." + filename;
        File scrfile2 = ((TakesScreenshot) chromeDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrfile2, new File(FILE_PATH + fileFormat));
    }

    public void checkSearchFieldInAnyNetwork(String networkNumber, String nameToSearch) throws IOException {
        WebElement network1Field = chromeDriver.findElement
                (By.cssSelector(networkNumber));
        network1Field.click();
        WebElement searchField = chromeDriver.findElement
                (By.cssSelector(Constants.SEARCH_FIELD_SELECTOR));
        searchField.click();
        searchField.sendKeys(nameToSearch);
        WebElement searchResult = chromeDriver.findElement
                (By.xpath(Constants.RESULT_FIELD_SELECTOR));
        Assert.assertTrue("Result Name Does Not Match Search Condition!",
                searchResult.getText()
                        .contentEquals(nameToSearch) );
        takeScreenshot(chromeDriver, nameToSearch + ".png");
        searchField.clear();
    }

    public void mainWindowTest() throws IOException {
        takeScreenshot(chromeDriver, "MainWindow.png");

        checkTitle(chromeDriver, Constants.MAIN_PAGE_TITLE);

        WebElement devicesButton = chromeDriver.findElement
                (By.cssSelector(Constants.DEVICE_BUTTON_SELECTOR));

        WebElement network1Field = chromeDriver.findElement
                (By.cssSelector(Constants.NETWORK_1_SELECTOR));

        Assert.assertTrue("Text Not Found", devicesButton.getText().contains("Устройства"));

        Assert.assertTrue("Text Not Found", network1Field.getText().contains("Сеть"));

        takeScreenshot(chromeDriver, "Result_field.png");
    }

    public void checkNetworkSelection() throws IOException {

        WebElement network1Field = chromeDriver.findElement
                (By.cssSelector(Constants.NETWORK_1_SELECTOR));

        WebElement network4321Field = chromeDriver.findElement
                (By.cssSelector(Constants.NETWORK_4321_SELECTOR));

        WebElement network9998Field = chromeDriver.findElement
                (By.cssSelector(Constants.NETWORK_9998_SELECTOR));

        network1Field.click();
        takeScreenshot(chromeDriver, "Network_1.png");

        network4321Field.click();
        takeScreenshot(chromeDriver, "Network4321.png");

        network9998Field.click();
        takeScreenshot(chromeDriver, "Network9998.png");
    }

    public void setStatsToDefaultOnSpecificNetwork(String networkFieldSelector) throws InterruptedException {

        WebElement networkField = chromeDriver.findElement
                (By.cssSelector(networkFieldSelector));

        networkField.click();

        Thread.sleep(1000);

        findRedAndYellowSeverityAndClickDeleteStatusButton();

    }

    public void findRedAndYellowSeverityAndClickDeleteStatusButton() {

        List<WebElement> redRows = chromeDriver.findElements(By.cssSelector(
                ("span.severity.red")));

        List<WebElement> yellowRows = chromeDriver.findElements(By.cssSelector(
                ("span.severity.yellow")));

        int rowCount = redRows.size() + yellowRows.size() + 2;

        for (int i = 2; i < rowCount; i++) {

            WebElement element = chromeDriver.findElement
                    (By.cssSelector
                            ("body > roc-root > div > infotecs-infotecs-devices > div.devices > div.devices_list > infotecs-devices-list > div.devices-list.table > table > tbody > tr:nth-child(" + i + ")"));

            element.click();

            try {
                WebElement setDefaultStatusButton = chromeDriver.findElement
                        (By.cssSelector(
                                ("body > roc-root > div > infotecs-infotecs-devices > div.devices > div.child_container > infotecs-events > div > div.device_details_header > div.error_panel.device_details_header_reset_status > button")));
                setDefaultStatusButton.click();
            } catch (NoSuchElementException ignored) { }
        }
    }

    public void historyButtonsScrollBackAndForward() {

        WebElement searchResult = chromeDriver.findElement
                (By.xpath(Constants.SEARCH_FIRST_RESULT_SELECTOR));

        searchResult.click();

        checkFieldsInResultWindow("Pushin Maksim Android");

        /* Press History Button 20 times */
        for (int i=0; i < 20; i++) {
            listHistoryTwentyTimesBack();
        }

        /* Press Back Button 20 times */
        for (int i=0; i < 20; i++) {
            listHistoryTwentyTimesForward();
        }
    }

    public void specificTextSearch() throws IOException {
        checkSearchFieldInAnyNetwork(Constants.NETWORK_1_SELECTOR, "Chapchaev");

        checkSearchFieldInAnyNetwork(Constants.NETWORK_4321_SELECTOR, "Klishin");

        checkSearchFieldInAnyNetwork(Constants.NETWORK_9998_SELECTOR, "Pushin");
    }


    public void checkFieldsInResultWindow(String name) {
        WebElement mainNameField = chromeDriver.findElement
                (By.xpath(Constants.NAME_MAIN_FIELD));
        Assert.assertTrue("Name not Found!", mainNameField.getText().contains(name));
    }

    public void listHistoryTwentyTimesBack() {
        WebElement historyButton = chromeDriver.findElement
                (By.cssSelector(Constants.HISTORY_BUTTON));
        historyButton.click();
    }

    public void listHistoryTwentyTimesForward() {
        WebElement historyButton = chromeDriver.findElement
                (By.cssSelector(Constants.FORWARD_BUTTON));
        historyButton.click();
    }


    public static void checkTitle(WebDriver chromeDriver, String expected) {
        Assert.assertEquals(expected, chromeDriver.getTitle());
    }
}
