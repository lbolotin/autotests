Документация по проекту  "akita-testing-template"
=================================================


Предварительные настройки
-------------------------

### Установить Intellij IDEA

> **Скачай последнюю версию здесь:** https://www.jetbrains.com/idea/download/

### Необходимые плагины для Intellij IDEA:

 > Cucumber for Java

 > Gherkin

 > Lombok

**Установка**

File/Settings/Plugins/Install JetBrains Plugins или с диска.

**Расположение**

 > https://plugins.jetbrains.com/idea/plugin/6317-lombok-plugin - откуда взять Lombok

 > https://plugins.jetbrains.com/?pr=idea - все плагины для IDEA

Для плагина **Lombok** в настройках:

* в разделе **Build, Execution, Deployment -> Compiler -> Annotation Processors**
требуется поставить галочку **Enable annotation processing**
* в разделе **Other settings -> Lombok plugin**
требуется поставить галочку **Enable lombok plugin for this project**

### Версия Java
Используются возможности Java 8

> **Скачай здесь** http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

**Проверка**
```
  java -version
```
### Скачивание Selenium Driver нужной версии

Важно, чтобы версия драйвера соответствовала версии браузера
Узнать о том, какая версия нужна тебе для браузера Chrome, и скачать ее можно с ресурса https://sites.google.com/a/chromium.org/chromedriver/downloads

### Установка пути до папки с selenium  драйверами

В зависимости от ОС выполни
```
  echo %PATH%
  или
  echo $PATH
```

 Для **Windows** тебе пригодится файл runMe.bat из http://mvn/artifactory/webapp/#/artifacts/browse/tree/General/testing-tools-binary/selenium-windows-drivers.zip

 Для **Linux**  и **Mac OS**  просто скачай драйверы для Chrome или Safari и пропиши путь до папки при необходимости:
 ```
  > gedit .bash_profile
  > PATH = /usr/local/bin/drivers:$PATH
  > source .bash_profile
 ```

### Установи себе git
Последнюю версию можно найти здесь: https://git-scm.com/downloads


Тестовый проект
---------------

### Запуск тестов

Скачанный проект с тестами нужно открыть как gradle-проект, далее ты можешь запускать тесты одним
из удобных тебе способов
* gradle задачи:  **Clean**, **Test**, **GenerateCucumberReport**
* контекстное меню для конкретного feature-файла, где следует не забывать про установку VM Properties.
  -Dbrowser=chrome
* из терминала
 ```
   > gradlew clean test -i
   > gradlew clean generateCucumberReport --debug
   > gradlew clean test -Pbrowser=chrome -Pprofile=local -PremoteHub=http://selgrid1/selenium-hub-robtestcredits/wd/hub
   > gradlew clean test -Dbrowser=chrome -Dcucumber.options="--tags @smoke"
  ```

  Для проектов предусмотрен параллельный запуск всех feature-файлов,
   запуск тестов на удаленной машине, смена бразера, глобальных настроек,
   запуск тестов согласно выставленным на сценариях тегам.


### Структура проекта

* папка **gradle** содержит в себе все необходимое для работы градловых команд.
      Самое важное здесь:
      distributionUrl=http\://mvn/artifactory/gradle-wrapper-remote-cache/distributions/gradle-2.14-bin.zip
      Он иногда перемещается
* файл **build.gradle** содержит  информацию о всех подгружаемых библиотеках и плагинах для нашего тестового проекта
В данном файле указывается то, где лежат классы с шагами:
```
generateRunner.glue = ["ru.alfabank.steps", "steps"]
```
* параметры по умолчанию для gradle задач указываются в **gradle.properties**
* файл **settings.gradle** содержит только название проекта
* файл **readMe.md** содержит документацию по возможностям библиотеки akita
 и подходам к написанию тестов
* папка **src** содержит все то,  что может потребоваться для написания тестов:
тела запросов, страницы, блоки,  сущности, конвертеры и классы шагов
* папка **src/main/java/restbodies** для файлов с телом запроса. На данную папку жестко привязан  шаг отправки запроса.
* раздел  **src/test** предназначен у нас только для хранения наших feature-файлов

Важно!!! Плагин, используемый нами для запуска тестов завязан на путь до feature-файлов
 src/test/resources/features. Не рекомендуется его изменять.

### Отчет о тестировании

   Akita позволяет генерировать красивые отчеты о прошедщем тестировании.
   Генерация отчета осуществляется запуском команды
   ```
   gradlew generateCucumberReport        или gradlew gCR
   ```

Отчет создается в папке  **build/reports**.  Основным является файл **"overview-features.html"**.

Создание тестов
---------------

###  Авторизация пользователя

 > Работает ли на вашем проекте авторизация по прямой ссылке? Без ввода логина и пароля?

Если работает, то Вам доступна возможность:
```
   Тогда я перешел по ссылке "{accUrl}?customerid=<userCus>"
```
Однако для данного шага требуется дальнейшая проверка
```
   Тогда страница "Счета и карты" загрузилась
```
В противном случае возможна авторизация следующим образом:
```
    Допустим открыта страница "Вход в Интернет-банк"
    И я успешно авторизовался как пользователь "userWithCredits"
    Тогда страница "Счета и карты" загрузилась
```

В данном случае **userWithCredits** - это переменная из **application.properties**.


###  Создание страницы/блока

Страница - это та базовая часть отображаемой страницы, которая несет для вас определенную смысловую нагрузку.
Т.е. Всплывающие окна, формы отображающиеся при заполнении определенных  полей или по нажатию на кнопки - это отдельная страница или блок, что в принципе ничем не отличается
от страницы.

**Пример странички:**

```
    @Slf4j
    @Name("Страница входа")
    public class LoginPage extends AlfaPage {

        @FindBy(css = "div.authorization__login-input input:first-child")
        @Name("Логин")
        private SelenideElement login;

        @FindBy(css = "div.input-password input:first-child")
        @Name("Пароль")
        private SelenideElement password;

        @FindBy(xpath = "//span[text()='Войти']/parent::button")
        @Name("Войти")
        private SelenideElement enterButton;

        public void signIn(String login, String password) {
            this.login.setValue(login).pressTab();
            this.password.setValue(password).pressTab();
            this.enterButton.click();
        }
      }
  ```


А как быть с блоками ссылок и кнопок с одинаковым поведением?

    public List<SelenideElement> getButtons(SelenideElement additionalContent){
            return additionalContent.$$(".account__credit-content .account__control-panel button");
        }

Что дает такой метод:
Предположим, что на форме находится 50 ссылок, для всех один и тот же локатор и отличаются они только текстом,
зачем создавать отдельно 50 элементов на странице, а вдруг их станет 40 или наоборот их количество увеличится до 60?

Все такие элементы можно получить сразу и работать с ними как с одним целым,
а отбирать  необходимое можно на основании текста, который видим в нашем приложении.
```
page.getButtons(page.getAddedContentForCredit()).stream()
                .filter(b -> <templateText>.equals(b.getText()))
                .findFirst()
                .orElseThrow(NoSuchElementException::new)
                .click();
```


### Инициализация страницы

Страница создается каждый раз, когда вызываются команды page(<Имя класса страницы>.class) или open(<URL страницы>,<Имя класса страницы>.class).

```
AccountsPage page = (AccountsPage) getCurrentPage();
alfaScenario.setCurrentPage(page.initialize().appeared());
```

Так создается страничка "Счета и карты", кроме того для страницы инициализируется карта ее элементов - это те поля, что помечены аннотацией @Name.
Кроме того осуществляется проверка того, что загружена требуемая страница.
Страница считается загруженной корректно, если за отведенное по умолчанию время были загружены основные ее элементы.
Основными элементами являются поля класа страницы с аннотацией @Name, но без аннотации @Optional.

Реализована возможность управления временем ожидания появления элемента на странице.
Чтобы установить  timeout, отличный от базового, нужно добавить в **application.properties**
строку
> waitingAppearTimeout=150000


### Доступ к элементам страницы

 > alfaScenario.getCurrentPage().getElement(elemName)

 > alfaScenario.getCurrentPage().getElementsList(elemName)

 Данные строки позволяют по имени элемента найти его в карте элементов текущей страницы.


### Текущая страница... Быть или не быть?

Пусть инициализирована страница:

```
AccountsPage page = (AccountsPage) getCurrentPage();
alfaScenario.setCurrentPage(page.initialize().appeared());
```

Тогда новую текущую страницу можно установить шагом

> Когда страница "<Имя страницы>" загрузилась

В коде это выглядит так:
```
 @Когда("^(?:страница|блок|форма) \"([^\"]*)\" (?:загрузилась|загрузился)$")
    public void loadPage(String nameOfPage) {
        alfaScenario.setCurrentPage(page(objectsSettings.getClassForPage(nameOfPage)).initialize().appeared());
    }
```

### Переменные: запись и чтение
Фреймворк позволяет заполнить хранилище переменных, существующее в рамках одного сценария, значениями и читать эти значения при необходимости.

Само хранилище представялет из себя экземляр HashMap<String, Object>.

Инициализация пустой карты переменных происходит в момент установки тестового окружения:
```
 @Before(order = 2)
    public void setScenario(Scenario scenario) throws Exception {
        alfaScenario.setEnvironment(new AlfaEnvironment(scenario));
    }
```
**Сохранение переменной в хранилище**:  alfaScenario.setVar(variable, <значение переменной>);

**Получение значения переменной из хранилища**: alfaScenario.getVar(<имя переменной>)

### Конвертеры из Json формата в cущности

Временами бывает полезно работать не с обычным ответом от реста, а с объектом (сущностью) или списком этих объектов.
Для этого может быть использован следующий подход:
* классы-заготовки, на примере такой сущности, как кредит.
* метод конвертации json в список сущностей

Структура класса должна соответствовать  response body конкретного REST-запроса. Например, так выглядит класс Кредит:

```
@Builder
@Data
@EqualsAndHashCode(callSuper=false)
public class Credit extends Entity{
    private String type;
    private BigDecimal limit;
    private String name;
    private NextPayment nextPayment;
    private MonthlyPayment monthlyPayment;
    private Account account;
    private String docNumber;
    private BigDecimal penalty;
    private BigDecimal fullRepaymentAmount;
    private BigDecimal minRepaymentAmount;
}
```

Теперь можно создать конвертер, который превращает ответ от REST запроса:
```
{
  "credits": [
    {
      "type": "ANNUITY",
      "limit": "46100000",
      "name": "Кредит наличными",
      "nextPayment": { ...
      }
      }]
}
```
в список кредитов:
```
 public List<Credit> getListOfCreditsFromResponse(String response){
        JsonArray creditsList = getJsonArrayFromString(response,"credits");
        List<Credit> credits = new Gson().fromJson(creditsList,
                new TypeToken<List<Credit>>() {
                }.getType());
        return credits;
 }

 public static JsonArray getJsonArrayFromString(String initialString, String nameOfArray) {
            Gson gson = new Gson();
            JsonElement element = gson.fromJson(initialString, JsonElement.class);
            JsonObject jsonObj = element.getAsJsonObject();
            return jsonObj.get(nameOfArray).getAsJsonArray();
 }
```

 **Пример кастомных шагов:**

```
 Тогда сформирован список кредитов из "creditsInfo"
 И в переменную "amountMonthly" сохранено "amount" из ежемесячного платежа для кредита со счетом "<accountNumber>"
```

### Управление настройками драйвера
Ниже указаны два метода Hook, которые выполняются до запуска сценария.
Эти методы позволяют задать тестовое окружение, очистить куки, определить где мы будем запускать тесты,
способ загрузки страниц, настройки размера экрана и неявные ожидания.
```
    @Before(order = 1)
        public static void clearCashAndDeleteCookies() {
            getWebDriver().manage().deleteAllCookies();
            if (!Strings.isNullOrEmpty(System.getProperty("remoteHub"))) {
                remote = System.getProperty("remoteHub");
                log.info("Тесты запущены на удаленной машине");
            } else
                log.info("Тесты будут запущены локально");
            pageLoadStrategy = "none";
            timeout = 20000;
        }
    }

    @Before(order = 2)
    public void setScenario(Scenario scenario) throws Exception {
        alfaScenario.setEnvironment(new AlfaEnvironment(scenario));
    }
```

### Отображение в отчете справочной информации
Для того, чтобы в отчете появился блок  Output с информацией, полезной для анализа отчета, можно воспользоваться следующим методом

> alfaScenario.write("Текущий URL = " + currentUrl + " \nОжидаемый URL = " + expectedUrl);

 **Когда полезно?**
В данном блоке можно вывести значение, сохраненное в конкретной переменной, взятое из поля на странице.

> Когда сохранено значение поля "Сумма для полного погашения" с типом сумма в переменную "fullAmount"

###  Скриншот при падении теста

Данная возможность реализована средствами cucumber hook для сценария
```
@After
public void takeScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            alfaScenario.sleep(1);
            final byte[] screenshot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
}
```

### Закрытие браузера

Текущая реализация предусматривает закрытие браузера после каждого сценария.
```
@After
public void closeWebdriver() {
        if (getWebDriver() != null) {
            WebDriverRunner.closeWebDriver();
        }
}
```

### Работа с REST запросами
В тестовом фреймворке реализована возможность отправки REST запросов и сохранения ответа в переменную.
Поддерживаются следующие типы запросов: **GET**, **POST**, **PUT**, **DELETE**.
```
 И вызван "POST" c URL "{creditsApi}credits/{docNumber}/repay", headers и parameters из таблицы. Полученный ответ сохранен в переменную "repayInfo"
       | type   | name          | value           |
       | header | applicationId | test            |
       | header | customerId    | <userCus>       |
       | body   | repayment     | <fileForCreate> |
```
В таблице переменных поддерживаются типы: header, parameter, body
Для body-параметра сейчас поддерживается как работа с телом запроса, хранящимся в папке restbodies, так и с указанием текста body в самом шаге в соответвующей ячейке

**Пример названия файла**: bodyForCreateRequestRepay.json

Важно помнить, что мы стараемся придерживаться BDD подхода и по возможности избегать использование технических шагов (как отправка REST запроса и сохранение ответа в переменную).
А сделать это просто - требуется лишь создать новый понятный шаг, который будет содержать базовый шаг отправки запроса.

Пример (основано на проекте "Кредиты"):
```
**Было

И вызван "GET" c URL "{creditsApi}credits", headers и parameters из таблицы. Полученный ответ сохранен в переменную "creditsInfo"
       | type   | name          | value           |
       | header | applicationId | test            |
       | header | customerId    | <userCus>       |
Тогда сформирован список кредитов из данных переменной "creditsInfo"

**Можно сделать так

 Тогда сформирован список кредитов

**Как сделать?

@И("^сформирован список кредитов$")
    public void saveCredits() throws Exception {
        String apiUrl = loadProperty("creditsApi");
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(apiUrl);
        urlBuilder.append("credits");
        Random random = new Random();
        String nameOfVar = "creditInfo" + random.nextInt(1000);
        List<RequestParam> params = new ArrayList<>();
        params.add(getApplicationId());
        params.add(getCustomerId());
        alfaScenario.write("api Url: " + urlBuilder.toString() + "\nparameters: " + params);
        defaultApiSteps.sendRequest("GET", urlBuilder.toString(), nameOfVar, params);
        accountsSteps.saveCreditsInVar(nameOfVar);
    }

```

### URL содержит переменные части

> public String getURLwithPathParamsCalculated(String urlName)

Данный метод позволяет заменять имена переменных в URL на их значения.


### Фильтрация сущностей - кастомный шаг

Предположим, что у нас есть кредиты и нам нужно отобрать только тот, который соответствуют выбранному условию,
а потом еще и забрать из кредита только определенную информацию.

Пример:
```
 @Когда("^в переменную \"([^\"]*)\" сохранено \"([^\"]*)\" из следующего платежа для кредита со счетом \"([^\"]*)\"$")
    public void setItemValueFromNextPay(String variable, String nameOfItem, String accountNumber) throws
            NoSuchFieldException {
        Optional<Credit> creditCurrent = objectsSettings.credits.get().stream().filter(credit -> credit.getAccount().getNumber().endsWith(accountNumber)).findFirst();
        if (creditCurrent.isPresent()) {
            Field field = creditCurrent.get().getNextPayment().getClass().getDeclaredField(nameOfItem);
            try {
                BigDecimal valueOfItem = new BigDecimal(creditCurrent.get().getNextPayment().getFieldValue(field).toString());
                alfaScenario.setVar(variable, valueOfItem);
                alfaScenario.write(variable + " = " + valueOfItem);
            } catch(NumberFormatException exp) {
                String valueOfItem = creditCurrent.get().getNextPayment().getFieldValue(field).toString();
                alfaScenario.setVar(variable, valueOfItem);
                alfaScenario.write(variable + " = " + valueOfItem);
            }
        } else {
            throw new IllegalStateException("Не найден кредит, соответствующий условию");
        }
    }
  ```

### Шаг как проверка логического выражения

У нас есть шаг, который например может выглядеть так:

> Тогда верно выражение "amountToPay == amountMonthly + penalty + 100"

Реализация на groovy. Важно, отметить, что равенство проверяется использованием операнда "==", неравенство, как "!="


### Общие шаги
 Все базовые шаги добавлены в библиотеку в класс DefaultSteps.  После того, как glue заполнен корректно, согласно указанной выше инструкции,
 тестировщик может пользоваться этими шагами при составлении новых сценариев.

### HealthCheck
По настройке healthCheck'a смотри доку на проект akita-healthcheck

### Как воспользоваться возможностями фреймворка?
Потребуется в каждом классе шагов прописать вот такую строку.

> AlfaScenario alfaScenario = AlfaScenario.getInstance();

Для страниц указать следующее:

> extends AlfaPage

А вот чтобы воспользоваться кастомными сущностями придется создать отдельный класс
со полями, которые будут содержать списки наших сущностей и которые будут уникальными в рамках потока.

 Чтобы тесты шарили между собой созданные вами промежуточные объекты, невидимые из фича-файлов, вам рекомендуется классы тестовых методов пометить аннотацией @ScenarioScoped и все поля
 (для промежуточных объектов) сделать обычными (без ThreadLocal).

### Динамические шаги на Cucumber?
Предположим, что есть примерно одинаковый набор сценариев, выполняющийся для разного набора тестовых данных и для некоторых из этих сценариев требуется исключить часть шагов или заменить их другими
В этом случае в помощь придет "Структура сценария с таблицей примеров".
Таблица примеров позволяет  выносить некоторые шаги в переменную таблицы таким образом:

```
**Было:
Сценарий:
Пусть приложение запущено для пользователя "piter"
Тогда загрузилась страница "Шаблон"
Тогда поле "Плашка успеха" отображается на экране

Сценарий:
Пусть приложение запущено для пользователя "semen"
Тогда загрузилась страница "Шаблон"
Тогда поле "Плашка успеха" не отображается на экране

**Стало:

Структура сценария:
Пусть приложение запущено для пользователя "<userName>"
Тогда загрузилась страница "Шаблон"
Тогда <successPanelStep>

Примеры:
|userName   | successPanelStep                                    |
|piter      | Тогда поле "Плашка успеха" отображается на экране   |
|semen      | Тогда поле "Плашка успеха" не отображается на экране|

```
Кроме того, можно задать пустое значение шага. Главное про реализацию такого пустого шага не забыть

Примеры feature-файлов
----------------------

* Обычный тест, можно указать несколько сценариев в рамках одной фичи, но нельзя несколько фичей указывать в одном файле.
#language: ru - строка для инициализации возможности писать фичи на русском языке
```
Функционал: Работоспособность перехода в старый Alfa-Click

  Сценарий: Переход в Кредиты старого Клика по нажатию на кнопку Вернуться в кредиты при осуществлении ежемесячного платежа

    Допустим открыта страница "Вход в Интернет-банк"
    И я успешно авторизовался как пользователь "userWithCredits"
    Тогда страница "Счета и карты" загрузилась
 ```
* В  Cucumber можно совсем не использовать ключевые слова для шагов, а выбранные ключевые слова не несут никакой логики, а служат лишь средством украшения текста

```
Функционал: Работоспособность перехода в старый Alfa-Click

      Сценарий: Переход в Кредиты старого Клика по нажатию на кнопку Вернуться в кредиты при осуществлении ежемесячного платежа

        * открыта страница "Вход в Интернет-банк"
        * я успешно авторизовался как пользователь "userWithCredits"
        * страница "Счета и карты" загрузилась
```

* Если ты хочешь сделать одни и те же проверки, но для разного набора входных данных, то тебе подойдет такой формат:

```
   Функционал: Форма ЧДП

     Структура сценария: Отмена частичного погашения кредита
        Тогда страница "Счета и карты" загрузилась
        Когда в блоке "Кредиты" выбрана карточка по номеру счета "<accountNumber>"
        И нажал на кнопку "Отменить досрочное погашение"
        Тогда ожидается появление уведомления с текстом "Заявка успешно отменена."

        Примеры:
          | accountNumber |
          | 0063          |
          | 1256          |
```
* Чтобы во всех тестах одного feature-файла  вынести одинаковые шаги в одно место,  можно воспользоваться предысторией.
  Предыстория выполнится для каждого теста в файле.

```
  Функционал: Ежемесячный платеж

    Данные тесты направлены на проверку работоспособности формы создания ежемесячного платежа.

    Предыстория: Переход на страницу ежемесячного платежа по прямой ссылке
      Пусть сохранено значение из глобальной переменной "accountsCurrentLinkUrl" в переменную "accUrl"
      И сохранено значение из глобальной переменной "creditsCurrentLinkUrl" в переменную "creditUrl"
      И сохранено значение из глобальной переменной "creditsApi" в переменную "creditsApi"
      Тогда я перешел по ссылке "{accUrl}?customerid=AA04K4"
      Когда вызван "GET" c URL "{creditsApi}credits", headers и parameters из таблицы. Полученный ответ сохранен в переменную "creditsInfo"
        | type   | name          | value  |
        | header | applicationId | test   |
        | header | customerId    | AA04K4 |
      Тогда сформирован список кредитов из "creditsInfo"
      Когда в переменную "docNumber" сохранено "docNumber" из основной информации о кредите со счетом "5607"
      И совершен переход на страницу "Ежемесячный платеж" по прямой ссылке = "{creditUrl}repay/{docNumber}"

    Структура сценария: Изменение суммы на счете, привязанном к кредиту после проведения оплаты, с подтверждением sms

      Тогда сохранено значение поля "Баланс на кредитном счете" с типом сумма в переменную "oldBalance"
      Когда очищено поле "Сколько платить"
      И установлено значение "<sum>" в поле "Сколько платить"
      И нажал на кнопку "Оплатить"
      Тогда страница "Подтверждение платежа" загрузилась
      Когда установлено значение "0000" в поле "Код подтверждения"
      И нажал на кнопку "Подтвердить"
      Тогда страница "Статус платежа" загрузилась

     Примеры:
          | sum |
          | 105 |

    Структура сценария: Исчезновение блока с подтверждением после нажатия на кнопку Отмена

        Когда очищено поле "Сколько платить"
        И установлено значение "<sum>" в поле "Сколько платить"
        Когда сохранено значение поля "Сколько платить" с типом сумма в переменную "sumOld"
        И нажал на кнопку "Оплатить"
        Тогда страница "Подтверждение платежа" загрузилась
        Тогда нажал на кнопку "Отмена"
        Тогда блок "Подтверждение платежа" исчез
        Тогда страница "Ежемесячный платеж" загрузилась
        Тогда значение input-поля "Сколько платить" содержит значение переменной "sumOld"

        Примеры:
          | sum |
          | 105 |
```

Заметки  писателя
-----------------

**< sum >** - получить значение переменной из раздела Примеры.

**{param}** - способ указания в Url-ах переменной части, которую нужно вычислить.

Если шаг подтягивается в формате **"страница|блок загрузилась|загрузился"**, то можно выбрать один из вариантов.
Варианты разделяются "|".






