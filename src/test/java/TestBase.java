import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://t-karta.ru/lk";
        Configuration.browserSize = "1280x1080";
        //держать браузер открытым после каждого теста
        Configuration.holdBrowserOpen = true;
    }
}
