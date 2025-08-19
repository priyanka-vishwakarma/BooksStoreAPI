# 📚 BookAPI QA Framework

BookAPI QA Framework is an API automation suite designed to validate the core features of a FastAPI-based BookStore application. It ensures reliable testing of CRUD operations, authentication flows, and error handling, with clear reporting and CI/CD integration.

---

## 🔧 Technology Stack

- **Java 17** – Framework logic  
- **RestAssured** – API automation and validation  
- **TestNG** – Test execution and configuration  
- **ExtentReports** – HTML-based test reporting  
- **Maven** – Build and dependency management  
- **GitHub Actions** – CI/CD automation  

---

## 📝 Test Coverage

The framework focuses on Health, User, and Book APIs, including:

### ✅ Positive Scenarios

- Service health validation  
- User registration and login  
- Create, read, update, delete book records  

### ❌ Negative Scenarios

- Invalid or missing request data  
- Unauthorized API access  
- Operations on non-existent books  
- Login/registration with invalid credentials  

---

## 🔗 Token Management

Authentication tokens from login responses are dynamically reused for secured API endpoints, supporting request chaining for dependent operations.

---

## 🏗 Framework Design

- **Modular Test Classes** – Separate classes for Health, User, and Book endpoints  
- **POJOs** – Map request and response objects for clarity and type safety  
- **Reusable Helpers** – Logging, token management, and validation utilities  
- **Test Lifecycle Management** – BaseTest and listeners initialize reporting and manage execution flow  

---

## 🔄 CI/CD Pipeline

This project integrates seamlessly with **GitHub Actions** for continuous testing and reporting.

### Trigger

- On every **push** or **pull request** to the `main` branch

### Steps

1. Checkout code  
2. Setup Java 17  
3. Start the FastAPI Bookstore API (see instructions below)  
4. Execute tests via Maven  
5. Upload reports: TestNG Surefire and ExtentReports  

---

### 📦 Bookstore API – Setup & Run Instructions

This project is a simple Bookstore API built with **FastAPI**. It allows user authentication (signup/login) and book management (create, update, delete, list books) with JWT-based security.

#### Prerequisites

- **Windows** with Python 3.7+ installed (tested with Python 3.13)  
- `pip` (comes with Python installation)  
- Command Prompt or PowerShell  

#### Setup Instructions

1. Navigate to the project folder:  
   Example:  
   ```bash
   cd "\bookstore 1\bookstore\bookstore"
   ```

2. Create a virtual environment (optional but recommended):  
   ```bash
   py -m venv venv
   venv\Scripts\activate
   ```

3. Install dependencies:  
   ```bash
   py -m pip install -r requirements.txt
   ```

#### Running the Application

Start the FastAPI app:
```bash
py -m uvicorn main:app --reload
```

Once running, open your browser and go to:  
[http://127.0.0.1:8000/docs](http://127.0.0.1:8000/docs)  

This will open the **Swagger UI**, where you can interactively test the APIs.

---

## 🧱 Project Structure

```
src/main/java      -> Utility classes and framework core  
src/test/java      -> Test classes for Health, User, Book  
config.properties  -> Base URL and authentication tokens  
test-output        -> TestNG and ExtentReports output  
```

---

## ▶️ Running Tests Locally

### Prerequisites

- Java 17+  
- Maven 3.6+  
- Git  

### Steps

1. **Clone the repository:**

```bash
git clone https://github.com/priyanka-vishwakarma/BooksStoreAPI.git
cd BooksStoreAPI
```

2. **Update `config.properties`:**

```properties
url=http://localhost:8000
```

3. **Execute tests:**

```bash
mvn clean test
```

4. **Open the generated report:**

```
test-output/Extent
