
# How UI starts & how screenshots work (current setup)

This documents what happens then screenshots attach:
- `ui/config/WebDriverConfig.java` — scenario-scoped, lazy WebDriver bean (Chrome)
- `test/hooks/DriverHooks.java` — `@Before/@After` restricted to `@ui` scenarios, teardown after UI
- `test/hooks/ScreenShotsHooks.java` — `@After("@ui")` that **unwraps the Spring proxy** (if any) and attaches a screenshot on failure

---

## High-level flow (text)

1. **Runner → Cucumber engine** discovers `@ui` scenario(s).  
2. **Cucumber ↔ Spring bridge** starts Spring test context (`@SpringBootTest`).  
3. **Beans are lazy**; nothing UI-related is created yet.  
4. **Before @ui** (DriverHooks): may perform lightweight init (does **not** force driver creation).  
5. **First UI step** or hook that actually touches the driver calls `ObjectProvider.getObject()` → Spring creates the **scenario-scoped** WebDriver bean:
   - WebDriverManager resolves ChromeDriver
   - ChromeOptions configured (headless/window size, etc.)
   - Spring returns a **scoped proxy** for the bean to the caller (JDK or class-based proxy).
6. Steps run, interacting with the page objects using the driver.
7. **After @ui (ScreenShotsHooks)**:
   - If scenario **failed**, it **unwraps** the Spring proxy (using `AopUtils`/`Advised`) to get the **target ChromeDriver**.
   - Casts to `TakesScreenshot`, grabs PNG bytes, `scenario.attach(...)`.
8. **After @ui (DriverHooks)** runs **after** the screenshot hook, calling `quit()` and cleaning up the session.

> If you switch the bean to return `ChromeDriver` and/or use `proxyMode = TARGET_CLASS`, the unwrap step becomes optional; the cast to `TakesScreenshot` will pass directly.

---

## Sequence (Mermaid)

```mermaid
sequenceDiagram
    participant J as JUnit Runner
    participant C as Cucumber Engine
    participant S as Spring Context
    participant H1 as DriverHooks (@Before/@After @ui)
    participant Steps as Step Classes
    participant SS as ScreenShotsHooks (@After @ui)
    participant W as WebDriver Bean (scenario-scoped)

    J->>C: run @ui scenario
    C->>S: bootstrap (CucumberContextConfiguration + SpringBootTest)
%%    Note over S: Beans are lazy; no driver yet
    C->>H1: @Before("@ui")
    H1-->>C: (no driver requested)
    C->>Steps: execute step that needs driver
    Steps->>S: request WebDriver via ObjectProvider.getObject()
    S->>W: instantiate scenario-scoped bean (WebDriverManager + ChromeOptions)
    W-->>Steps: proxy (points to ChromeDriver)
    Steps-->>C: interactions done
    C->>SS: @After("@ui") (scenario failed)
    SS->>S: obtain WebDriver (proxy)
    SS->>SS: unwrap proxy via AopUtils/Advised
    SS->>W: cast to TakesScreenshot and get PNG
    SS-->>C: attach screenshot to report
    C->>H1: @After("@ui")
    H1->>W: quit()
    H1-->>C: done
```

