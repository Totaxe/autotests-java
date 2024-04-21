An example of UI and API automated tests using [Selenium](https://github.com/SeleniumHQ/selenium) and [Retrofit](https://github.com/square/retrofit) libraries.

A similar project using FluentLenium framework can be found [here](https://github.com/Totaxe/autotests-fluentlenium).

## Application Under Test
[LinkAce](https://github.com/Kovah/LinkAce) is a bookmark management web application written in PHP. It uses MySQL (MariaDB) to store data.

The app has API. There are examples of queries in linkace.http file.

How to install with Docker:
```
git clone https://github.com/Totaxe/autotests-java.git
cd docker
docker compose up -d
```

## Running tests
All tests:
```
chmod a+x gradlew
./gradlew cleanTest test
```
UI only:
```
./gradlew cleanTest test --tests com.example.selenium.ui.*
```
API only:
```
./gradlew cleanTest test --tests com.example.selenium.api.*
```
Test report is generated by Gradle in build/reports/tests/test/index.html.

## Settings
Test settings are in src/test/test.properties. You can also specify settings in environment variables or java system properties.

The browser is specified in the **browser** setting. It can be chrome or firefox.

Tests can be executed on [Selenoid](https://github.com/aerokube/selenoid) (RemoteWebDriver). The Selenoid url is specified in **hubUrl** setting, e.g. http://192.168.0.157:4444/wd/hub. To run tests locally **hubUrl** must be empty.

Logging settings are in src/test/logback-test.xml.

## Libraries Used
| Name | Description |
| -------- | -------- |
| [JUnit 5](https://github.com/junit-team/junit5)  | Testing framework |
| [AssertJ](https://github.com/assertj/assertj)  | Test assertions |
| [Selenium](https://github.com/SeleniumHQ/selenium) | UI testing |
| [Retrofit](https://github.com/square/retrofit) | API testing |
| [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) | Browser driver management |
| [OWNER](https://github.com/matteobaccan/owner) | Settings |
| [Logback](https://github.com/qos-ch/logback) (SLF4J) | Logging |
| [Lombok](https://github.com/projectlombok/lombok) | Boilerplate code generation |
| [MyBatis](https://github.com/mybatis/mybatis-3) | SQL script execution |
| [Opencsv](https://opencsv.sourceforge.net/) | CSV parser |
| [OkHttp](https://github.com/square/okhttp) | HTTP client |

## Jenkins
Tests can be run on Jenkins. The pipeline script is in the Jenkinsfile.

The **junit** step generates report that can be viewed in the Jenkins UI.

The page screenshot is made after each test and attached to the report using [JUnit Attachments](https://plugins.jenkins.io/junit-attachments/) plugin.
