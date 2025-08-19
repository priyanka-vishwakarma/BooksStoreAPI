📚 BookAPI QA Framework

BookAPI QA Framework is an API automation suite designed to validate the core features of a FastAPI-based BookStore application. It ensures reliable testing of CRUD operations, authentication flows, and error handling, with clear reporting and CI/CD integration.

🔧 Technology Stack

Java 17 – Framework logic

RestAssured – API automation and validation

TestNG – Test execution and configuration

ExtentReports – HTML-based test reporting

Maven – Build and dependency management

GitHub Actions – CI/CD automation

📝 Test Coverage

The framework focuses on Health, User, and Book APIs, including:

✅ Positive Scenarios

Service health validation

User registration and login

Create, read, update, delete book records

❌ Negative Scenarios

Invalid or missing request data

Unauthorized API access

Operations on non-existent books

Login/registration with invalid credentials

🔗 Token Management

Authentication tokens from login responses are dynamically reused for secured API endpoints, supporting request chaining for dependent operations.

🏗 Framework Design

Modular Test Classes – Separate classes for Health, User, and Book endpoints

POJOs – Map request and response objects for clarity and type safety

Reusable Helpers – Logging, token management, and validation utilities

Test Lifecycle Management – BaseTest and listeners initialize reporting and manage execution flow

⚙️ CI/CD Pipeline

Triggered on every push or pull request to main

Steps:

Checkout code

Setup Java 17

Execute tests via Maven

Upload reports: TestNG Surefire and ExtentReports

🧱 Project Structure
src/main/java      -> Utility classes and framework core
src/test/java      -> Test classes for Health, User, Book
config.properties  -> Base URL and authentication tokens
test-output        -> TestNG and ExtentReports output

▶️ Running Tests Locally
Prerequisites

Java 17+

Maven 3.6+

Git

Steps

Clone the repository:

git clone https://github.com/priyanka-vishwakarma/BooksStoreAPI.git
cd BooksStoreAPI


Update config.properties:

url=http://localhost:8000


Execute tests:

mvn clean test


Open the generated report:

test-output/ExtentReport.html

📊 Reporting

Shows test name, status (pass/fail/skip), request/response details, and assertions

HTML reports are easy to navigate and include all test results

🤝 Contributing

Fork the repository

Create a feature branch: git checkout -b feature/your-feature

Commit and push your changes

Submit a pull request

✍️ Author

Priyanka Vishwakarma
