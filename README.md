# Инструкция по запуску: #
Перед запуском приложения можем задать настройки (выбрать валюту (поле «rate.base»), по отношению к которой смотрится курс(по умолчанию задан рубль - RUB), задать ключевые слова для отображени гифок (раздел Keywords) и т.д.). Можно указать любой код из перечня поддерживаемых валют, представленного на сайте https://docs.openexchangerates.org/docs/supported-currencies Ключевые слова также могут быть любые.

Настройки находятся в файле:

/src/main/resources/ application.properties (доступно после клона проекта)

Запуск:

git clone https://github.com/agenttim/alfa-bank-testing.git </br>
cd alfa-bank-testing </br>
gradle clean </br>
gradle build </br>
cd build/libs </br>
java -jar exchange-rates-0.0.1-SNAPSHOT.jar </br>
Далее в браузере открываем: http://localhost:8080/compare/USD </br>
(вместо USD можем ввести любой код из перечня поддерживаемых валют, представленного на сайте https://docs.openexchangerates.org/docs/supported-currencies)

В зависимости от того, вырос курс или нет, будет показана гифка по ключевому слову rich или broke соответственно. Если курс не изменился, то увидим гифку по ключевому слову meditation. Такую ситуацию можно смоделировать самостоятельно, отправив запрос http://localhost:8080/compare/RUB.