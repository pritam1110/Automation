# Adactin Selenium Automation Framework - Enhanced

This framework supports Cross-Browser Testing (Chrome, Firefox, Edge), TestNG Groups (Smoke, Sanity, Regression, E2E), Priority execution, and ThreadLocal parallel execution.

---

## Complete List of Demonstration Maven Commands

### 1. Cross-Browser Execution
- **Run suite with default `testng.xml` (Cross-browser Chrome + Firefox in parallel):**
  ```bash
  mvn clean test
  ```
- **Force execution on Chrome:**
  ```bash
  mvn clean test -Dbrowser=chrome
  ```
- **Force execution on Firefox:**
  ```bash
  mvn clean test -Dbrowser=firefox
  ```
- **Force execution on Edge:**
  ```bash
  mvn clean test -Dbrowser=edge
  ```

---

### 2. Group Execution (Include / Exclude)
- **Run ONLY `smoke` group:**
  ```bash
  mvn clean test -Dgroups=smoke
  ```
- **Run `smoke` and `sanity` groups:**
  ```bash
  mvn clean test -Dgroups=smoke,sanity
  ```
- **Run `regression` group:**
  ```bash
  mvn clean test -Dgroups=regression
  ```
- **Run all tests EXCEPT `regression` group:**
  ```bash
  mvn clean test -Dexcludegroups=regression
  ```
- **Run `smoke` tests on Firefox:**
  ```bash
  mvn clean test -Dgroups=smoke -Dbrowser=firefox
  ```

---

### 3. Class & Method Selective Execution
- **Run a specific class:**
  ```bash
  mvn clean test -Dtest=LoginTest
  ```
- **Run a specific test method:**
  ```bash
  mvn clean test -Dtest=LoginTest#verifyLoginPageTitle
  ```
- **Run multiple specific classes:**
  ```bash
  mvn clean test -Dtest=LoginTest,SearchHotelTest
  ```

---

### 4. Passing Credentials Dynamically
- **Run all tests with credentials:**
  ```bash
  mvn clean test -Dusername=myuser -Dpassword=mypass
  ```
- **Run `regression` group with credentials on Chrome:**
  ```bash
  mvn clean test -Dgroups=regression -Dbrowser=chrome -Dusername=myuser -Dpassword=mypass
  ```
