package helpers;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.TestInfo;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import static tests.TestBase.getTestInfo;
import static tests.TestBase.getTestPath;
import static utils.FileUtils.savePng;

public class ScreenshotComparisonHelper {
    private String pathToResources = "src/test/resources/screenshots/";


    public Screenshot takeActualScreenshot() {
        String testPath = getTestPath();

        Screenshot takeScreenshot = new AShot().takeScreenshot(WebDriverRunner.getWebDriver());

        savePng(takeScreenshot.getImage(), pathToResources + testPath + "actual.png");

        return takeScreenshot;
    }
}
