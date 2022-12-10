package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    private SelenideElement
            cardPan = $(".card-details__pan");

    public void logout(){
        $(".dropdown").click();
        $(".dropdown__container").find(byText("Выйти")).click();
    }

    public void verifyCardPan(){
        cardPan.shouldHave(Condition.text("9643100203300380992"));
    }
}
