package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import helpers.ScreenshotComparisonHelper;
import org.junit.jupiter.api.Test;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GoogleTests extends TestBase {

    @Test
    void fullMapTest() {
        open("http://maps.google.com");

        // take actual screenshot
        Screenshot actualImage = new ScreenshotComparisonHelper()
                .takeActualScreenshot();

        // load expected screenshot
        Screenshot expectedImage = new ScreenshotComparisonHelper()
                .getExpectedScreenshot();

        // assert difference
        ImageDiff diff = new ScreenshotComparisonHelper().compareScreenshots(
                actualImage,
                expectedImage,
                10);
        assertFalse(diff.hasDiff(), "Screenshot has difference");

    }

    @Test
    void fullMapElementTest() {
        open("http://maps.google.com");

        // take actual screenshot
        SelenideElement onlyMap = $(".widget-scene");
        Screenshot actualImage = new ScreenshotComparisonHelper()
                .takeActualScreenshot(onlyMap);

        // load expected screenshot
        Screenshot expectedImage = new ScreenshotComparisonHelper()
                .getExpectedScreenshot();

        // assert difference
        ImageDiff diff = new ScreenshotComparisonHelper().compareScreenshots(
                actualImage,
                expectedImage,
                10);
        assertFalse(diff.hasDiff(), "Screenshot has difference");

    }

    @Test
    void partMapTest() {
        open("http://maps.google.com");

        // take actual screenshot
        Screenshot actualImage = new ScreenshotComparisonHelper()
                .takeActualScreenshot();

        // load expected screenshot

        // assert difference

    }
}
