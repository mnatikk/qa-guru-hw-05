package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class AuthPage {
    private final String MAIN_TITLE = "Вход в личный кабинет";

    private SelenideElement
            mainTitle = $(".main__title"),
            email = $("#username"),
            password = $("#password"),
            submit = $("#submit-button");

    public AuthPage openPage() {
        //открываем страницу
        open("https://t-karta.ru/lk");

        //проверка, что форма открылась, чтобы не ходить во все тесты если завалилась
        mainTitle.shouldHave(Condition.text(MAIN_TITLE));
        submit.shouldHave(Condition.text("Войти"));
        submit.shouldHave(Condition.disabled);
        return this;
    }

    public AuthPage setEmail(String value){
        email.setValue(value);
        return this;
    }

    public AuthPage setPassword (String value){
        password.setValue(value);
        return this;
    }

    public AuthPage submitAuth (){
        submit.click();
        return this;
    }

    public AuthPage verifyError (){
        $(".error").shouldHave(Condition.text("Неверное имя пользователя или пароль."));
        return this;
    }
}
