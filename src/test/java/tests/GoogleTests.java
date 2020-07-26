package tests;

import com.codeborne.selenide.Configuration;
import helpers.ScreenshotComparisonHelper;
import org.junit.jupiter.api.Test;
import ru.yandex.qatools.ashot.Screenshot;

import static com.codeborne.selenide.Selenide.open;

public class GoogleTests extends TestBase {

    @Test
    void mapTest() {
        open("http://maps.google.com");

        // take actual screenshot
        Screenshot actualImage = new ScreenshotComparisonHelper()
                .takeActualScreenshot();

        // load expected screenshot

        // assert difference

    }
}
