# Movie Reservation System

[Project Detail](https://roadmap.sh/projects/movie-reservation-system)

This project is the backend service for a modern Movie Reservation System, built with Spring Boot and designed using the principles of Spring Modulith. It provides a robust, scalable, and maintainable foundation for managing movies, showtimes, user bookings, and authentication.

---

## ✨ Features

- **User Authentication**: Secure user registration and login using JWT (JSON Web Tokens).
- **Role-Based Access Control**: Distinction between `USER` and `ADMIN` roles, with specific permissions for administrative endpoints.
- **Movie Management**: Admins can perform CRUD (Create, Read, Update, Delete) operations on the movie catalog.
- **Showtime Management**: Admins can create, update, and delete showtimes for each movie.
- **Booking System**: Authenticated users can book tickets for available showtimes (First-Come, First-Served).
- **Modular Architecture**: Built with Spring Modulith to ensure high cohesion and low coupling between different business domains (`users`, `movies`, `bookings`, etc.).
- **Event-Driven Updates**: Asynchronous, event-driven communication between modules. For example, a new booking automatically triggers an update to the available seats for a showtime.

---

## 🛠️ Technologies Used

- **Java 17**
- **Spring Boot 3**
- **Spring Security**: For authentication and authorization.
- **Spring Data JPA**: For database interaction.
- **Spring Modulith**: For enforcing a modular application architecture.
- **PostgreSQL** (or any JPA-compatible database)
- **Maven**: For dependency management.
- **Lombok**: To reduce boilerplate code.
- **JJWT**: For creating and parsing JSON Web Tokens.

---

## 🏛️ Architecture: Spring Modulith

The application is organized into distinct modules, each representing a core business capability. This design makes the system easier to understand, develop, and maintain.

- **`users`**: Manages user accounts, authentication (login/register), and security configuration.
- **`movies`**: Manages the movie catalog.
- **`showtimes`**: Manages the schedule of movies, linking them to specific screens and times.
- **`bookings`**: Handles the core logic of creating and retrieving user reservations. It publishes an event upon successful booking.

The modules communicate in two ways:
1.  **Direct Calls**: For synchronous queries and validations (e.g., the `bookings` service calls the `showtimes` service to check for available seats).
2.  **Events**: For asynchronous, reactive workflows (e.g., the `bookings` service publishes a `BookingCompleted` event, and the `showtimes` module listens for it to update the seat count).

---

## 🚀 Getting Started

### Prerequisites

- JDK 17 or later
- Maven 3.8 or later
- PostgreSQL (or another configured database) running on your machine.

### Installation & Setup

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/siddharth-ranjan/movie-reservation-system.git](https://github.com/your-username/movie-reservation-system.git)
    cd movie-reservation-system/backend
    ```

2.  **Configure the database:**
    Open `src/main/resources/application.properties` and update the `spring.datasource` properties to match your database configuration.

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/your_db_name
    spring.datasource.username=your_db_user
    spring.datasource.password=your_db_password
    spring.jpa.hibernate.ddl-auto=update
    ```

3.  **Configure JWT Secret:**
    In the same `application.properties` file, set a strong, secret key for signing JWTs.
    ```properties
    app.jwt-secret=yourSuperSecretKeyThatIsVeryLongAndSecureChangeThis
    app.jwt-expiration-milliseconds=86400000 # 24 hours
    ```

4.  **Build and run the application:**
    ```bash
    mvn spring-boot:run
    ```
    The application will start on `http://localhost:8080`.

---

## 🔑 API Endpoints

### Authentication (`/api/users`)

- `POST /api/users/register`: Register a new user.
- `POST /api/users/login`: Log in to get a JWT.

### Bookings (`/api/bookings`)

- `POST /api/bookings`: Create a new booking (requires authentication).
- `GET /api/bookings/showtime/{showtimeId}`: Get the current user's booking for a specific showtime (requires authentication).

### Admin Endpoints (`/admin/**`)

*Requires `ADMIN` role.*

- `POST /admin/movies`: Add a new movie.
- `POST /admin/showtimes`: Create a new showtime.
- `GET /admin/bookings`: Get all bookings or filter by `showtimeId`.
    - Example: `GET /admin/bookings?showtimeId=1`

*(This is a summary. A full API specification, e.g., using Swagger/OpenAPI, would be a great addition.)*

---

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a pull request.
