import org.junit.*;

import java.io.IOException;

public class GeneralStatServerTest extends AbstractWebTest {

    @BeforeClass
    public static void setUp() throws InterruptedException {
        AbstractWebTest.setUp();
    }

    @Test
    public void checkMainWidnowFields() throws IOException {
        mainWindowTest();
    }

    @Test
    public void checkTextSearchInSpecificNetwork() throws IOException {
        specificTextSearch();
    }

    @Test
    public void checkNetworkFieldsIndication() throws IOException {
        checkNetworkSelection();
    }

    /* Temporary Disabled
    @Test
    public void checkHistoryButtons()  {
        historyButtonsScrollBackAndForward();
    }
    */

    @AfterClass
    public static void tearDown() {
        chromeDriver.quit();
    }
}
