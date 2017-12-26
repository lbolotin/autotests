#language:ru

#пример написания интеграционного теста, который проверяет,
# что список категорий расходов, который отображается на странице,
# соответствует списку, который вернул сервис retail-pfm-expenses-api
Функционал: Отображение блока категорий расходов

  Структура сценария: Отображение списока категрий расходов соответствует ответу сервиса retail-pfm-expenses-api
    Допустим совершен переход на страницу "Страница входа" по ссылке из property файла "pfmMainPageUrl"
    Когда пользователь "<user>" ввел логин и пароль
    Тогда страница "Расходы и доходы" загрузилась
    Когда выполнен POST запрос на URL "retail-pfm-expenses-api" с headers и parameters из таблицы. Полученный ответ сохранен в переменную "userExpenses"
      | type   | name          | value                |
      | header | customerId    | {<user>}             |
      | header | applicationId | PFM                  |
      | header | User-Timezone | Europe/Moscow        |
      | header | Content-Type  | application/json     |
      | body   | request       | { "months": <date> } |
    И в переменную "expensesCategoriesList" сохранен список категорий расходов из переменной "userExpenses" за месяцы "<date>"
    Тогда список категорий на главной странице соответствует REST ответу из переменной "expensesCategoriesList"

    Примеры:
      | user                     | date        |
      | userWithManyTransactions | ["2017-10"] |