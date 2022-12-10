import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideLocaleTests {

    static Stream<Arguments> locateMenu() {
        return Stream.of(
                Arguments.of("RU", List.of("С чего начать?", "Док", "ЧАВО", "Блог", "Javadoc", "Пользователи","Отзывы")),
                Arguments.of("EN", List.of("Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes"))
        );
    }


    @DisplayName("Проверяем, что в локале {1}, кнопки имеют названия на языке локали: {2}")
    @MethodSource
    @ParameterizedTest
    void locateMenu(String locale, List<String> buttons){
        open("https://ru.selenide.org");
        $$("#languages a").find(Condition.text(locale)).click();
        $$(".main-menu-pages a").filter(Condition.visible).shouldHave(CollectionCondition.texts(buttons));
    }
}
