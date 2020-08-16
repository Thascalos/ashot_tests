package helpers;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.Coords;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import static tests.TestBase.getTestPath;
import static utils.FileUtils.*;

public class ScreenshotComparisonHelper {
    private final String pathToResources = "src/test/resources/screenshots/";


    public Screenshot takeActualScreenshot() {
        String testPath = pathToResources + getTestPath();

        Screenshot takeScreenshot = new AShot().takeScreenshot(WebDriverRunner.getWebDriver());

        savePng(takeScreenshot.getImage(), testPath + "actual.png");

        return takeScreenshot;
    }

    public Screenshot takeActualScreenshot(SelenideElement selenideElement) {
        String testPath = pathToResources + getTestPath();

        Screenshot takeScreenshot = new AShot()
                .coordsProvider(new WebDriverCoordsProvider())
                .shootingStrategy(ShootingStrategies.simple())
                .takeScreenshot(WebDriverRunner.getWebDriver(), selenideElement);

        savePng(takeScreenshot.getImage(), testPath + "actual.png");

        return takeScreenshot;
    }

    public Screenshot takeActualScreenshot(Coords area) {
        String testPath = pathToResources + getTestPath();

        Screenshot takeScreenshot = new AShot()
            .coordsProvider(new WebDriverCoordsProvider())
            .addIgnoredArea(area) // todo Домашнее задание
            .shootingStrategy(ShootingStrategies.simple())
            .takeScreenshot(WebDriverRunner.getWebDriver());

        savePng(takeScreenshot.getImage(), testPath + "actual.png");

        return takeScreenshot;
    }

    public Screenshot takeActualScreenshot(String element) {
        String testPath = pathToResources + getTestPath();

        Screenshot takeScreenshot = new AShot()
                .coordsProvider(new WebDriverCoordsProvider())
                .addIgnoredElement(By.cssSelector(element)) // todo Домашнее задание
                .shootingStrategy(ShootingStrategies.simple())
                .takeScreenshot(WebDriverRunner.getWebDriver());

        savePng(takeScreenshot.getImage(), testPath + "actual.png");

        return takeScreenshot;
    }

    public Screenshot getExpectedScreenshot() {
        String testPath = pathToResources + getTestPath();

        Screenshot expectedScreenshot = new Screenshot(getImage(testPath + "expected.png"));

        return expectedScreenshot;
    }

    @Step("Compare screenshots")
    public ImageDiff compareScreenshots(Screenshot actualImage, Screenshot expectedImage, int diffSizeTrigger) {
        String testPath = pathToResources + getTestPath();

        Allure.addAttachment("Actual", getInputStream(testPath + "actual.png"));
        Allure.addAttachment("Expected", getInputStream(testPath + "expected.png"));

        ImageDiff diff = new ImageDiffer().makeDiff(expectedImage, actualImage).withDiffSizeTrigger(diffSizeTrigger);

        if (diff.hasDiff()) {
            savePng(diff.getMarkedImage(), testPath + "difference.png");
            Allure.addAttachment("Difference", getInputStream(testPath + "difference.png"));
        }

        return diff;
    }
}
