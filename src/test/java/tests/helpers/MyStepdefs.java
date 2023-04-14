package tests.helpers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import pages.YandexMarketFilters;
import pages.YandexMarketMainPage;
import pages.YandexMarketResults;
import java.util.Arrays;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;
/**
 * Класс используется для описания логики выполнения сценариев
 *
 * @author Горячев Роман Юрьевич
 */
public class MyStepdefs {
    /**
     * Объект YandexMarketMainPage
     *
     * @author Горячев Роман Юрьевич
     */
    private final YandexMarketMainPage yandexMarketMainPage = new YandexMarketMainPage();
    /**
     * Объект YandexMarketFilters
     *
     * @author Горячев Роман Юрьевич
     */
    private final YandexMarketFilters yandexMarketFilters = new YandexMarketFilters();
    /**
     * Объект YandexMarketResults
     *
     * @author Горячев Роман Юрьевич
     */
    private final YandexMarketResults yandexMarketResults = new YandexMarketResults();
    /**
     * Метод для перехода на заданный в tests.properties сайт
     *
     * @author Горячев Роман Юрьевич
     */
    @Given("перейти на сайт ЯндексМаркет")
    public void перейти_на_сайт_ЯндексМаркет() {
        open(Properties.testsProperties.yandexmarketUrl());
    }
    /**
     * Метод для открытия каталога
     *
     * @author Горячев Роман Юрьевич
     */
    @Then("открыть каталог")
    public void открытьКаталог() {
        yandexMarketMainPage.getCatalogue().click();
    }
    /**
     * Метод для перехода в заданную категорию
     *
     * @author Горячев Роман Юрьевич
     * @param catalogueSection наименование раздела каталога
     * @param category категория
     */
    @Then("выбрать раздел (.*) и категорию (.*)")
    public void выбратьРазделРаздел_каталогаИКатегориюКатегория(String catalogueSection, String category) {
        SelenideElement cataloguePart = $x("//li[@data-zone-name='category-link']//span[translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ',"
                + "'abcdefghijklmnopqrstuvwxyzабвгдеёжзийклмнопрстуфхцчшщъыьэюя')='" + catalogueSection.toLowerCase() + "']");
        cataloguePart.shouldBe(enabled);
        actions().moveToElement(cataloguePart).perform();
        SelenideElement categoryElem = $x("//div[@role='tabpanel']//div[translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ',"
                + "'abcdefghijklmnopqrstuvwxyzабвгдеёжзийклмнопрстуфхцчшщъыьэюя')='" + category.toLowerCase() + "']");
        categoryElem.shouldBe(visible);
        categoryElem.click();
    }
    /**
     * Метод для проверки, что текущая категория соответствует заданной
     *
     * @author Горячев Роман Юрьевич
     * @param category категория
     */
    @Then("проверить, что текущая категория (.*)")
    public void проверитьЧтоТекущаяКатегорияКатегория(String category) {
        yandexMarketResults.getCurrentCategoryArea().shouldBe(visible);
        String currentCategory = Arrays.asList(yandexMarketResults.getCurrentCategoryArea().getText().split("\\r?\\n")).stream()
                .reduce((first, second) -> second).toString();
        System.out.println(currentCategory);
        System.out.println(category);
        Assert.assertTrue("Текущая категория не - " + category, currentCategory.toLowerCase().contains(category.toLowerCase()));
    }
    /**
     * Метод для установки фильтра по производителю
     *
     * @author Горячев Роман Юрьевич
     * @param producer производитель
     */
    @Then("установить фильтр по производителю (.*)")
    public void установитьФильтрПоПроизводителюПроизводитель(String producer) {
        yandexMarketFilters.getAllFilters().click();
        yandexMarketFilters.getAllProducers().click();
        SelenideElement producerElem = $x("//div[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ'," +
                "'abcdefghijklmnopqrstuvwxyzабвгдеёжзийклмнопрстуфхцчшщъыьэюя')='" + producer.toLowerCase() + "']");
        producerElem.shouldBe(Condition.visible);
        producerElem.click();
        yandexMarketFilters.getShowGoods().click();
    }
    /**
     * Метод для проверки, что в результатах поиска содержатся товары с заданным производителем и перелистыванием страниц если присутствует кнопка "Вперёд"
     *
     * @author Горячев Роман Юрьевич
     * @param producer производитель
     */
    @Then("проверить, что результаты поиска соответствуют производителю (.*)")
    public void проверитьЧтоРезультатыПоискаСоответствуютПроизводителюПроизводитель(String producer) {
        if(producer.equalsIgnoreCase("Apple")){
            producer = "Iphone";
        }
        while (true) {
            yandexMarketResults.getLoading().shouldNotBe(Condition.visible);
            ElementsCollection articles = yandexMarketResults.getArticles();
            for (int i = 0; i < articles.size(); i++) {
                articles.get(i).scrollTo();
                Assert.assertTrue("В резульататх поиска не найдено товара с производителем: " + producer + " в статье под номером " + i,
                        articles.get(i).getText().toLowerCase().contains(producer.toLowerCase()));
            }
            if (yandexMarketResults.getPager().getText().contains("Вперёд")) {
                yandexMarketResults.getForward().click();
            }  else break;
        }
    }
}