package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;
/**
 * Класс используется для задания фильтров на странице результатов поиска ЯндексМаркета в паттерне PageObject
 *
 * @author Горячев Роман Юрьевич
 */
@Getter
public class YandexMarketFilters{
    /**
     * Элемент страницы - Все фильтры
     *
     * @author Горячев Роман Юрьевич
     */
    SelenideElement allFilters = $x("//button//span[text() = 'Все фильтры']");
    /**
     * Элемент страницы - Показать ещё
     *
     * @author Горячев Роман Юрьевич
     */
    SelenideElement allProducers = $x("//span[text() = 'Показать ещё']");
    /**
     * Элемент страницы - Показать товары, соответветствующие заданному фильтру
     *
     * @author Горячев Роман Юрьевич
     */
    SelenideElement showGoods = $x("//a[contains(text(), 'Показать')]");
}