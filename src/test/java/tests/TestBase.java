package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class TestBase {

    static TestInfo testInfo;

    public static TestInfo getTestInfo() {
        return testInfo;
    }

    public static String getTestPath() {
        String className = testInfo.getTestClass().get().getSimpleName();
        String methodName = testInfo.getTestMethod().get().getName();

        return className + "/" + methodName + "/";
    }

    @BeforeAll
    @Step("Tests setup")
    public static void beforeAll() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
//        Configuration.startMaximized = true;
    }

    @BeforeEach
    public void beforeEach(TestInfo info) {
        testInfo = info;
    }
}
