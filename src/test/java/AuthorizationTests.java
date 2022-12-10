import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import pages.AuthPage;
import pages.MainPage;
import java.util.stream.Stream;


public class AuthorizationTests {
    AuthPage authPage = new AuthPage();
    MainPage mainPage = new MainPage();

    static Stream<Arguments> incorrectAuth_example_MethodSource(){
        return Stream.of(
                Arguments.of("1","1"),
                Arguments.of("2","3"),
                Arguments.of("mnatikk","123")
                );
    }

    @BeforeEach
    void setUp(){
        authPage.openPage();
    }

    @Disabled
    @DisplayName("Успешная авторизация, пользователь зарегистрирован")
    @Tag("BLOCKER")
    @Test
    void correctAuth (){
        authPage.setEmail("####")       //Sensetive data
                .setPassword("####")       //Sensetive data
                .submitAuth();
        mainPage.verifyCardPan();
        mainPage.logout();
    }

    @DisplayName("Передача через CsvSources. Авторизация не валидна для пользователя {1} c паролем {2}")
    @Tag("BLOCKER")
    @CsvSource(value = {
            "1,1",
            "2,3",
            "mnatikk,123"
    })
    @ParameterizedTest
    void incorrectAuth (String login, String password){
        authPage.setEmail(login)
                .setPassword(password)
                .submitAuth();
        authPage.verifyError();
    }

    @DisplayName("Передача через CsvFileSources. Авторизация не валидна для пользователя {1} c паролем {2}")
    @CsvFileSource (resources = "AuthIncorrectCredentials.csv", delimiter = '|')
    @ParameterizedTest
    void incorrectAuth_example_CsvFileSources (String login, String password){
        authPage.setEmail(login)
                .setPassword(password)
                .submitAuth();
        authPage.verifyError();
    }

    @DisplayName("Передача через MethodSource. Авторизация не валидна для пользователя {1} c паролем {2}")
    @MethodSource
    @ParameterizedTest
    void incorrectAuth_example_MethodSource (String login, String password){
        authPage.setEmail(login)
                .setPassword(password)
                .submitAuth();
        authPage.verifyError();
    }

}
