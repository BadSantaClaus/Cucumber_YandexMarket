package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import asserts.Assertions;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
/**
 * Класс используется для задания переменных и методов на странице результатов поиска ЯндексМаркета в паттерне PageObject
 *
 * @author Горячев Роман Юрьевич
 */
@Getter
@Setter
public class YandexMarketResults {
    /**
     * Элемент страницы - Рекомендации
     *
     * @author Горячев Роман Юрьевич
     */
    SelenideElement recommendations = $x("//h2[contains(text(), 'Рекомендации')]");
    /**
     * Элемент страницы, содержащий нумерацию страниц
     *
     * @author Горячев Роман Юрьевич
     */
    SelenideElement pager = $x("//div[@data-baobab-name='pager']");
    /**
     * Элемент страницы, содержащий кнопку "Вперёд"
     *
     * @author Горячев Роман Юрьевич
     */
    SelenideElement forward = $x("//div[@data-baobab-name='next']");
    /**
     * Элемент страницы, содержащий загрузочный экран
     *
     * @author Горячев Роман Юрьевич
     */
    SelenideElement loading = $x("//div[@data-apiary-widget-name = '@marketfront/SearchSerp']//div[@data-auto= 'preloader']");
    /**
     * Список результатов поиска
     *
     * @author Горячев Роман Юрьевич
     */
    ElementsCollection articles = $$x("//article[contains(@data-zone-data, 'shopId')]");
    /**
     * Элемент страницы, содержащий текущую категорию
     *
     * @author Горячев Роман Юрьевич
     */
    SelenideElement currentCategoryArea = $x("//nav[@aria-label='Вы здесь']");
}
