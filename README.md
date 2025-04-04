# ZenClinic

ZenClinic is a veterinary clinic management system built with Spring Boot. It allows veterinary clinics to manage their vets, pet owners, pets, and visit records.

## Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Running the Application](#running-the-application)
- [Database Configuration](#database-configuration)
  - [H2 Console Access](#h2-console-access)
  - [Database Schema](#database-schema)
- [Sample Data](#sample-data)
- [Project Structure](#project-structure)
- [Troubleshooting](#troubleshooting)
- [Maven Commands Reference](#maven-commands-reference)
  - [Basic Commands](#basic-commands)
  - [Spring Boot Specific Commands](#spring-boot-specific-commands)
  - [Notes on Maven Wrapper](#notes-on-maven-wrapper)
- [Contributing](#contributing)
- [License](#license)

## Features

- Manage veterinarians and their specialties
- Track pet owners and their contact information
- Record pet details including type, breed, and birth date
- Schedule and document pet visits, diagnoses, and treatments
- Dashboard with recent visits and statistics
- H2 database for data persistence

## Technologies

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Thymeleaf for server-side rendering
- H2 Database (file-based for persistence)
- Maven for dependency management
- Bootstrap for responsive UI

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or higher
- Maven 3.6.x or higher
- Git (optional, for cloning the repository)

### Installation

1. Clone the repository (or download the ZIP file):

```bash
git clone https://github.com/yourusername/ZenClinic.git
cd ZenClinic
```

2. Build the project using Maven:

```bash
./mvnw clean install
```

## Running the Application

1. Start the application using Maven:

```bash
./mvnw spring-boot:run
```

   This command uses the Maven wrapper (`mvnw`) to run the Spring Boot application. The Spring Boot Maven plugin's `run` goal starts the application.

   For a clean start that rebuilds everything from scratch, use:

```bash
./mvnw clean spring-boot:run
```

   This command:
   - `clean`: Deletes all compiled classes and resources from the `target` directory
   - `spring-boot:run`: Compiles the code and starts the Spring Boot application

   Using `clean` is helpful when you want to ensure all previous compiled files are removed and the application is built from scratch, which can resolve certain issues after code changes.

2. Access the application in your web browser:

```
http://localhost:8081
```

3. To stop the application, press `Ctrl+C` in the terminal where it's running.

## Database Configuration

ZenClinic uses an H2 database in file mode for data persistence. The database file is created in the project root directory.

### H2 Console Access

The H2 console is enabled for easy database management:

1. Access the H2 console at:
```
http://localhost:8081/h2-console
```

2. Use the following connection details:
   - JDBC URL: `jdbc:h2:file:./zenclinicdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE`
   - Username: `sa`
   - Password: (leave empty)
   - Click "Connect"

### Database Schema

The database schema is automatically created and updated by Hibernate based on the entity classes. The main tables are:

- `vets` - Veterinarians
- `specialties` - Veterinary specialties
- `vet_specialties` - Junction table for vet-specialty relationship
- `owners` - Pet owners
- `pets` - Pets
- `pet_types` - Types of pets (dog, cat, etc.)
- `visits` - Visit records

## Sample Data

The application is pre-populated with sample data on first run, including:

- 5 veterinarians with various specialties
- 5 specialties (Radiology, Surgery, Dentistry, Cardiology, Dermatology)
- 6 pet types (Dog, Cat, Bird, Rabbit, Fish, Reptile)
- 3 pet owners
- 4 pets
- 4 visit records

This data is only loaded if the database is empty, so it won't be reloaded on subsequent application starts.

## Project Structure

```
ZenClinic/
├── src/
│   ├── main/
│   │   ├── java/com/zendemo/zenclinic/
│   │   │   ├── config/           # Configuration classes
│   │   │   ├── controller/       # MVC controllers
│   │   │   ├── model/            # Entity classes
│   │   │   ├── repository/       # Spring Data repositories
│   │   │   ├── service/          # Business logic services
│   │   │   └── ZenClinicApplication.java  # Main application class
│   │   └── resources/
│   │       ├── static/           # Static resources (CSS, JS)
│   │       ├── templates/        # Thymeleaf templates
│   │       └── application.properties  # Application configuration
│   └── test/                     # Test classes
├── .gitignore
├── mvnw                          # Maven wrapper script
├── mvnw.cmd                      # Maven wrapper script for Windows
├── pom.xml                       # Maven project configuration
└── README.md                     # This file
```

## Troubleshooting

### Database Issues

If you encounter database-related issues:

1. Delete the database file:
```bash
rm -f ./zenclinicdb*
```

2. Restart the application:
```bash
./mvnw spring-boot:run
```

### Port Already in Use

If port 8081 is already in use, you can change the port in `application.properties`:

```properties
server.port=8082  # Change to any available port
```

### Memory Issues

If you encounter memory issues, you can increase the heap size:

```bash
./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Xmx512m"
```

## Maven Commands Reference

ZenClinic uses Maven for build automation and dependency management. Here are the key Maven commands you might need:

### Basic Commands

- **Clean and compile the project**:
  ```bash
  ./mvnw clean compile
  ```

- **Run tests**:
  ```bash
  ./mvnw test
  ```

- **Package the application (creates a JAR file)**:
  ```bash
  ./mvnw package
  ```

- **Install the package to local Maven repository**:
  ```bash
  ./mvnw install
  ```

### Spring Boot Specific Commands

- **Run the application**:
  ```bash
  ./mvnw spring-boot:run
  ```

- **Clean and run the application**:
  ```bash
  ./mvnw clean spring-boot:run
  ```

- **Run with specific profile**:
  ```bash
  ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
  ```

- **Run with increased memory**:
  ```bash
  ./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Xmx512m"
  ```

- **Build an executable JAR**:
  ```bash
  ./mvnw clean package
  ```
  The JAR will be created in the `target` directory and can be run with:
  ```bash
  java -jar target/zenclinic-0.0.1-SNAPSHOT.jar
  ```

### Notes on Maven Wrapper

- The project uses Maven Wrapper (`mvnw`), which automatically downloads the correct Maven version if needed
- On Windows, use `mvnw.cmd` instead of `./mvnw`
- If you have Maven installed globally, you can use `mvn` instead of `./mvnw`

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.