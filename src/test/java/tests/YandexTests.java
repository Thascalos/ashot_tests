package tests;

import helpers.ScreenshotComparisonHelper;
import org.junit.jupiter.api.Test;
import ru.yandex.qatools.ashot.Screenshot;

import static com.codeborne.selenide.Selenide.open;

public class YandexTests extends TestBase {

    @Test
    void mapTest() {
        open("http://yandex.ru/maps");

        // take actual screenshot
        Screenshot actualImage = new ScreenshotComparisonHelper()
                .takeActualScreenshot();

        // load expected screenshot

        // assert difference

    }
}
