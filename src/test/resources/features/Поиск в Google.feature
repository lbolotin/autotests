#language:ru

#пример написания интеграционного теста, который проверяет,
# что список категорий расходов, который отображается на странице,
# соответствует списку, который вернул сервис retail-pfm-expenses-api
Функционал: Поиск в гугле

  Предыстория: Загрузка поисковой системы
    Допустим совершен переход по ссылке "google"
    Тогда страница "GoogleMain" загрузилась

  Сценарий: Поиск червячка Кирилла
    Допустим в поле "Строка поиска" введено значение "Червячок Кирилл"
    И выполнено нажатие на клавиатуре "<Enter>"
    Тогда блок "GoogleSearch" загрузился
    Когда выполнено нажатие на поле "Первый элемент"

  Структура сценария: ценарий: Поиск Yandex по 3 и 4 элементу
    Допустим в поле "Строка поиска" введено значение "Yandex"
    И выполнено нажатие на клавиатуре "<Enter>"
    Тогда блок "GoogleSearch" загрузился
    Когда выполнено нажатие на поле "<Номер элемента>"

    Примеры: Номер элемента
      | Номер элемента    |
      | Третий элемент    |
      | Четвертый элменет |
