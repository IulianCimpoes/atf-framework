## 📊 Framework Boot Diagram

This diagram shows how Spring, Cucumber, Selenium, and the modular components of the ATF framework interact.

![Framework Diagram](main/docs/framework-diagram.png)

TestRunner is launched via JUnit.

Cucumber picks up glue from provided packages.

CucumberSpringConfiguration connects Spring context.

Spring boots TestApplication (if necessary).

Step definitions and hooks are loaded.

Hooks like DriverHooks run setup logic.

Steps use WebFormPage to interact with the application.

Hooks tear down the driver after tests finish.

Reports are generated via Cucumber HTML plugins.