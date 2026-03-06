# Selenium Java Automation Assessment

A comprehensive Selenium WebDriver automation framework built with Java, TestNG, and Extent Reports for testing web applications.

## 📋 Table of Contents

- [Overview](#overview)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Test Reports](#test-reports)
- [Test Data](#test-data)
- [Contributing](#contributing)

## 🎯 Overview

This project demonstrates a robust Page Object Model (POM) based automation framework for web application testing. It includes comprehensive test coverage for user journeys including login, product selection, checkout, and order verification.

## 🛠 Technologies Used

- **Java 17** - Programming language
- **Selenium WebDriver 4.40.0** - Browser automation
- **TestNG 7.11.0** - Testing framework
- **Extent Reports 5.1.2** - Test reporting
- **Apache POI 5.5.1** - Excel data handling
- **Jackson 2.19.2** - JSON data processing
- **Log4j 3.0.0** - Logging framework
- **Maven** - Build and dependency management

## 📁 Project Structure

```
SelenuimJavaAssessment_NdosiBootCamp/
├── src/
│   ├── main/java/org/example/
│   │   └── Main.java
│   └── test/
│       ├── java/
│       │   ├── base/
│       │   │   └── Base.java                 # Base test class with setup/teardown
│       │   ├── Basics/
│       │   │   ├── Actions.java              # Common action methods
│       │   │   ├── DataFunction.java         # Data management utilities
│       │   │   ├── ExcelReader.java          # Excel file reader
│       │   │   ├── JsonReader.java           # JSON file reader
│       │   │   └── ReportingUtils.java       # Reporting utilities
│       │   ├── Pages/                        # Page Object Model classes
│       │   │   ├── InventoryPage.java
│       │   │   ├── LandingPage.java
│       │   │   ├── LearnPage.java
│       │   │   ├── LoginPage.java
│       │   │   ├── LogoutPage.java
│       │   │   └── OrderPreviewPage.java
│       │   ├── ReportUtils/                  # Reporting utilities
│       │   │   ├── ExtentReportManager.java
│       │   │   └── Listener.java             # TestNG listener for reporting
│       │   ├── Tests/
│       │   │   └── Pablo_Test.java           # Main test class
│       │   └── Utilities/
│       │       ├── BrowserFactory.java       # WebDriver factory
│       │       └── Screenshots.java          # Screenshot utilities
│       └── resources/
│           ├── ConfigFiles/
│           │   └── Config.json               # Application configuration
│           └── TestData/
│               └── myTestData.xlsx           # Test data spreadsheet
├── testRunner/
│   └── testNG.xml                            # TestNG suite configuration
├── target/                                   # Build output directory
│   ├── Reports/                              # Generated test reports
│   └── Screenshots/                          # Captured screenshots
├── pom.xml                                   # Maven configuration
└── README.md                                 # This file
```

## 📋 Prerequisites

Before running this project, ensure you have the following installed:

- **Java 17** or higher
- **Maven 3.6+**
- **Chrome Browser** (latest version recommended)
- **ChromeDriver** (automatically managed by WebDriverManager)

## 🚀 Installation & Setup

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd SelenuimJavaAssessment_NdosiBootCamp
   ```

2. **Install dependencies:**
   ```bash
   mvn clean install
   ```

3. **Verify setup:**
   ```bash
   mvn test-compile
   ```

## ⚙️ Configuration

### Application Configuration

Edit `src/test/resources/ConfigFiles/Config.json`:
```json
{
  "Url": "https://ndosisimplifiedautomation.vercel.app/",
  "Browser": "chrome"
}
```

### Test Data

Test data is stored in `src/test/resources/TestData/myTestData.xlsx`. The framework supports:
- Excel spreadsheets for structured test data
- JSON files for configuration data
- Dynamic data loading through `DataFunction.java`

## 🧪 Running Tests

### Run All Tests
```bash
mvn test
```

### Run Specific Test Suite
```bash
mvn test -DsuiteXmlFile=testRunner/testNG.xml
```

### Run Tests with Custom Configuration
```bash
mvn test -Dbrowser=chrome -Durl=https://your-test-url.com
```

### Test Execution Flow

The test suite executes the following user journey:
1. **Landing Page Test** - Verify landing page elements
2. **Login Test** - User authentication
3. **Learn Page Test** - Navigate and verify learn section
4. **Inventory Page Test** - Product selection and configuration
5. **Customer Selection Test** - Device and service selection
6. **Order Preview Test** - Order review and discount application
7. **View Invoice Test** - Final invoice verification

## 📊 Test Reports

### Extent Reports

Test reports are automatically generated in HTML format with:
- ✅ Detailed test execution logs
- ✅ Screenshots embedded in test details
- ✅ Test status (Pass/Fail/Skip)
- ✅ Execution timestamps
- ✅ System information

**Report Location:** `target/Reports/Pablo_AutomationReport.html`

### Features:
- **Dark Theme** - Modern UI for better readability
- **Screenshot Integration** - Images displayed in details section
- **Test Categorization** - Clear pass/fail/skip indicators
- **System Info** - OS and execution machine details

### Viewing Reports

1. Run your tests
2. Open `target/Reports/Pablo_AutomationReport.html` in any browser
3. Navigate through test results with embedded screenshots

## 📈 Test Data Management

The framework supports multiple data sources:

### Excel Data
- Located in `src/test/resources/TestData/myTestData.xlsx`
- Supports multiple sheets for different test scenarios
- Read using `ExcelReader.java`

### JSON Configuration
- Application settings in `Config.json`
- Test parameters and environment configurations

### Data Functions
- `DataFunction.java` provides centralized data access
- Supports both Excel and JSON data formats
- Dynamic data loading for parameterized tests

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Create a Pull Request

### Code Standards
- Follow Java naming conventions
- Add comments for complex logic
- Update tests for new features
- Ensure all tests pass before submitting

## 📝 Notes

- The framework uses Chrome as the default browser
- Screenshots are automatically captured on test completion
- Reports include timestamps for better traceability
- Thread-safe implementation for parallel execution support

## 🆘 Troubleshooting

### Common Issues:

1. **ChromeDriver not found**
   - Ensure Chrome browser is installed
   - WebDriverManager handles driver downloads automatically

2. **Test data not loading**
   - Verify file paths in `DataFunction.java`
   - Check Excel file format and sheet names

3. **Reports not generating**
   - Ensure write permissions for `target/` directory
   - Check Extent Reports version compatibility

---

**Author:** Pablo Automation  
**Date:** March 2026  
**Framework Version:** 1.0.0
