package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
/**
 * Класс используется для задания переменных и методов на главной странице ЯндексМаркета в паттерне PageObject
 *
 * @author Горячев Роман Юрьевич
 */
@Getter
public class YandexMarketMainPage {
    /**
     * Элемент страницы - каталог
     *
     * @author Горячев Роман Юрьевич
     */
    SelenideElement catalogue = $x("//button[@aria-controls='catalogPopup']");
}