import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SetAllFieldsToDefault extends AbstractWebTest {

    @BeforeClass
    public static void setUp() throws InterruptedException {
        AbstractWebTest.setUp();
    }

    @Test
    public void setDefaultConditionOnNetworkOne() throws InterruptedException {
            setStatsToDefaultOnSpecificNetwork(Constants.NETWORK_1_SELECTOR);
    }

    @Test
    public void setDefaultConditionOnNetwork4321() throws InterruptedException {
            setStatsToDefaultOnSpecificNetwork(Constants.NETWORK_4321_SELECTOR);
    }

    @Test
    public void setDefaultConditionOnNetwork9998() throws InterruptedException {
            setStatsToDefaultOnSpecificNetwork(Constants.NETWORK_9998_SELECTOR);
    }

    @AfterClass
    public static void tearDown() {
        chromeDriver.quit();
    }
}
