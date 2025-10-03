# Bookstore API Test Suite

Automated tests for a RESTful Bookstore API, covering core operations such as retrieving, creating, updating, and deleting books. Built with Java 25, TestNG, RestAssured, and Allure for reporting.

---

## Technologies Used

- **Java 25**
- **TestNG** – test framework
- **RestAssured** – HTTP client for API testing
- **Jackson Databind** – JSON serialization/deserialization
- **Maven** – build and dependency management
- **Allure Report** – test reporting

---

## Covered Test Cases

Book API Coverage:

| Endpoint                | Description                                    |
|-------------------------|------------------------------------------------|
| `GET /Books`            | Retrieve all books                             |
| `GET /Books/{id}`       | Retrieve a specific book by ID                 |
| `POST /Books`           | Create a new book                              |
| `PUT /Books/{id}`       | Update an existing book                        |
| `DELETE /Books/{id}`    | Delete a book                                  |
| `GET /Books/99999`      | Retrieve non-existent book                     |
| `PUT /Books/999999`     | Update non-existent book                       |

Author API Coverage:

| Endpoint                | Description                                    |
|-------------------------|------------------------------------------------|
| `GET /Authors`          | Retrieve all authors                           |
| `GET /Authors/{id}`     | Retrieve author by ID                          |
| `POST /Authors`         | Create a new author                            |
| `PUT /Authors/{id}`     | Update author                                  |
| `DELETE /Authors/{id}`  | Delete author by ID                            |
| `GET /Authors/99999`    | Retrieve non-existent author                   |

Tests are chained using `dependsOnMethods` to ensure proper execution order.

---

## How to Run Tests

Make sure you have Maven installed and configured.

`mvn clean test`

## How to Generate Allure Report

If you have Allure CLI installed:
```
allure generate target/allure-results --clean -o target/allure-report
allure open target/allure-report
```

If you don't have Allure CLI installed:
You can install it using one of the following methods:

Install via Homebrew (macOS):
`brew install allure`

Install via Scoop (Windows):
`scoop install allure`

Install via NPM (cross-platform):
`npm install -g allure-commandline --save-dev`

After installation, verify it works:
`allure --version`
