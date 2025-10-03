---

## Spring Boot Application: Car Park Management System

A simple RESTful API built with Spring Boot for managing car parking syatem. It supports CRUD operations, pagination, and basic validation.

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
| MYSQL Database     | In-memory database              |
| Maven              | Build and dependency management |

---

### Project Structure

```
src/
├── main/
│   ├── java/com/wego/flights/     ...contain main application file
│   │   ├── aspect/                ...contains ExceptionControllerAdvice for handlingresponse in case of exceptions
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
(above token if changed please generate one)

# Build the project
mvn clean install

#create local mysql DB named wego as per property shown below
spring.datasource.url=jdbc:mysql://localhost:3306/wego
If another name required change in property also

# As of now query is being logged. If want to remove logging of query 
comment below mentioned property from application.properties
spring.jpa.show-sql=true

# Run the application
mvn spring-boot:run

#Index to be applied on the fasten up the get queries. As of now :
1. CREATE INDEX idx_lots_coords ON car_park_info (lots_available, x_cord, y_cord);
2. CREATE INDEX idx_car_park_no ON car_park_info(car_park_no);
```

---

### API Endpoints

| Method | Endpoint                                                      | Description                                        |
|--------|---------------------------------------------------------------|----------------------------------------------------|
| GET    | `/update-parking`                                                | Update parking lot data by calling downstream api  |
| PUT    | `/carparks/nearest?latitude=1.37326&longitude=103.897&page=1&per_page=3` | Get nearest parking details                        |


---

### Future Enhancements

#### Tech Improvements:
Rate Limiting can be applied to prevent unfair APIS calls
Caching layer can be implemented for frequent queries
Job can be made to update parking lot periodically
Spatial Index can be used for fater queries. As of now for smaller datasets composit may work.

#### Security Improvements
Authentication and Autherization to be applied
Health Check & monitoring
Audit logging

#### Enhancement -Business Tasks
Live map Integration
Push Notification
Search by location and landmark
Filter and Short Option

---

### 🙋‍♂️ Author

- **Sumit Kumar** – [mailto:- sumit.insta91@gmail.com]
- GitHub: [@PlayProCode](Assignment repo: https://github.com/PlayProCode/assignment)

---
