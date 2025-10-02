---

## Spring Boot Application: Car Park Management System

A simple RESTful API built with Spring Boot for managing parking system. It supports CRUD operations, pagination, and basic validation.

---

### Features

- Update parking data in accordance with external API 
- Conditionally fetch conditional available parkings
- Pagination and geolocation based sorting 
- Input validation
- Exception handling using @ControllerAdvice
- MYSQL database (for development)

---

### Tech Stack

| Technology         | Description                     |
|--------------------|---------------------------------|
| Java 17            | Programming language            |
| Spring Boot 3.4.10 | Backend framework               |
| Spring Data JPA    | ORM and database access         |
| MYSQL Database     | RDBMS database              |
| Maven              | Build and dependency management |

---

### Project Structure

```
src/
├── main/
│   ├── java/com/wego/flights/     ...contains main application file
│   │   ├── aspect/                ...contains ExceptionControllerAdvice for handling response in case of exceptions
│   │   ├── constants/             ...to keep constant files 
│   │   ├── controller/            ...for controllers
│   │   ├── entity/                ...for entities
│   │   ├── repository/            ...to place repositories
│   │   ├── service/               ...service interface
│   │   │      └── impl/           ...service interface implementations
│   │   └── utils/                 ...to keep util files
│   └── resources/
│       ├── application.properties  ... properties
│       └── static/                 ... static data, in our case to load static data related to parking
├── llm/                            ... contains prompts.md file to showcase llm usage
```

---

### Setup Instructions

#### Prerequisites

- Java 17+
- Maven 3.8+
- IDE (IntelliJ, VS Code, Eclipse)

#### Run Locally

```bash
# Clone the repository
git clone https://github.com/your-username/employee-management.git
cd wego-flights

pat token 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

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

---

### API Endpoints

| Method | Endpoint                                                                  | Description                                        |
|--------|---------------------------------------------------------------------------|----------------------------------------------------|
| GET    | `/update-parking`                                                         | Update parking lot data by calling downstream api  |
| PUT    | `/carparks/nearest?latitude=1.37326&longitude=103.897&page=1&per_page=3`  | Get nearest parking details                        |


---

### 🙋‍♂️ Author

- **Sumit Kumar** – [mailto-sumit.insta91@gmail.com]
- GitHub: [@PlayProCode]([https://github.com/PlayProCode/assignment])
- Assignment: [https://github.com/PlayProCode/assignment/tree/main/wego-flights] 
---
