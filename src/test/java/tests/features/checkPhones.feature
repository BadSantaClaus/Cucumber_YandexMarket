 Feature: Проверка категории смартфоны сайта ЯндексМаркет
   Проверяем категорию и результаты поиска

   @run
   Scenario Outline: Проверяем текущую категорию
     Given перейти на сайт ЯндексМаркет
     Then открыть каталог
     Then выбрать раздел <раздел_каталога> и категорию <категория>
     Then проверить, что текущая категория <категория>
     Then установить фильтр по производителю <производитель>
     Then проверить, что результаты поиска соответствуют производителю <производитель>

     Examples:
       | раздел_каталога | категория | производитель |
       | электроника     | смартфоны | google        |