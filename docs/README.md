# Дипломный проект профессии «Тестировщик»
## процедура запуска авто-тестов

1. Склонировать проект на локальный репозиторий командой ```git clone``` и открыть 
его в приложении IntelliJ IDEA

2. Запустить Docker Desktop

3. В терминале запустить контейнеры с СУБД и симулятором
```
docker-compose up
```
4. Во втором терминале запустить SUT:
```
java -jar ./artifacts/aqa-shop.jar 
```
5. Запустить тесты кнопкой run или:
```
./gradlew clean test
```
6. Открыть Gradle отчёт(index.html) из папки 
build/reports/tests/test
7. Для остановки работы контейнеров выполнить команду:
```
docker-compose down
```

## Документация
1. [Текст задания](https://github.com/netology-code/qa-diploma)
2. [План](https://github.com/TszyaoEkaterina/qa-diploma/blob/master/docs/Plan.md)
3. [Отчёт о проведённом тестировании](https://github.com/TszyaoEkaterina/qa-diploma/blob/master/docs/Report.md)
4. [Отчётные документы по итогам автоматизации](https://github.com/TszyaoEkaterina/qa-diploma/blob/master/docs/Summary.md)
