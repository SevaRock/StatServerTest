import org.junit.*;

import java.io.IOException;

public class GeneralStatServerTest extends AbstractWebTest {

    @BeforeClass
    public static void setUp() throws InterruptedException {
        AbstractWebTest.setUp();
    }

    @Ignore
    @Test
    public void checkMainWidnowFields() throws IOException {
        mainWindowTest();
    }

    @Ignore
    @Test
    public void checkTextSearchInSpecificNetwork() throws IOException {
        specificTextSearch();
    }

    @Ignore
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
