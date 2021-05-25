# Gradle jUnit5 RestAssured Lombok AssertJ

A sample gradle base project which uses jUnit5 for running tests and RestAssured framework for API
testing. Framework also uses Lombok to simplify work with POJO and AssertJ to empower assertions.

# To Run

* will run all the tests inside `src/test/tests`.
  ```shell
  $ gradlew clean test --tests tests.* -i
  ```
* will run all the tests inside `src/test/tests` on Prod env.
  ```shell
  $ gradlew clean test --tests tests.* -i -Denv=prod
  ```

After the tests are ran, you can see:

* jUnit test reports under `build/reports/tests/index.html`
* Allure results `build/allure-results`
* Allure report `build/reports/allure-report`

## Allure configuration

* [Allure CLI](https://docs.qameta.io/allure/#_commandline) should be installed
* Allure results stored in `build/allure-results`
* Allure report stored in `build/reports/allure-report`
* To open allure report
  ```shell
  $ allure open path_to_allure_report_folder
  ```

### Allure RestAssured configuration

In the `RestService.class` we are adding `AllureRestAssured` logging filter to request
specification, this will add request/response logs as attachments to Allure report

## Framework configuration

Framework follows the idea that each endpoint should have own `Service` class which provides
interface for endpoint manipulations. Base request/response specifications are configured
in `RestService.class`. `RestWrapper.class` provides interface for all services.

### API used

[Cat API](https://documenter.getpostman.com/view/5578104/RWgqUxxh#intro)

### AppConfiguration

This class makes generic configuration for the framework and allows us to run tests on different
environments by passing system property.

### AllureEnvironmentWriter

This class adds framework configuration details to "Environments" section of Allure report

### ConfigurationSetupTierDownHook

Setup hook which initializing framework constants `AppConfiguration.initialize()` before test run.
Each test should be extended with the hook class `@ExtendWith(ConfigurationSetupTierDownHook.class)`

## Libraries Used

* [Junit](https://junit.org/junit5/docs/current/user-guide/) - 5.7.0
* [Gradle](https://gradle.org/guides) - 6.8.3
* [RestAssured](https://rest-assured.io/) - 4.3.3
* [Lombok](https://projectlombok.org/) - 6.0.0-m2
* [AssertJ](https://assertj.github.io/doc/) - 3.19.0
* [Jackson](https://www.baeldung.com/jackson) - 2.12.3


