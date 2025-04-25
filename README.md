# Scripto

**Scripto** is a simple command-line interface (CLI) application built in Java to track your development projects. It helps you keep track of the status, progress, programming languages, and other details of your various projects.

### Features
- Track project status (In Progress, Completed, etc.)
- Keep record of the programming language used for each project
- Lightweight CLI app that runs in the terminal

---

## Requirements

- Java 17 or higher
- Maven

---

## Installation

1. Clone the repository to your local machine:
   
   ```bash
   git clone https://github.com/TejasDsouza7/Scripto.git
   cd Scripto
   ```

## Build the Project:

 Use Maven to build the project:

   ```bash
mvn clean package
```

This will generate two JAR files in the target/ folder:

Scripto-1.0-SNAPSHOT.jar` (does not include dependencies)

Scripto-1.0-SNAPSHOT-shaded.jar (includes all dependencies)

Running the Application
Using the Shaded JAR (Recommended)
To run the shaded JAR (which contains all dependencies, including Gson):

```bash
java -jar target/Scripto-1.0-SNAPSHOT-shaded.jar
```
Using the Regular JAR (Without Dependencies)
If you prefer to use the regular JAR (you must manage dependencies manually):

Ensure all dependencies are available via Maven or classpath configuration.

Run the regular JAR with:

```bash
java -jar target/Scripto-1.0-SNAPSHOT.jar
```
Note: This will only work if all dependencies are included in the classpath.

### Usage
Once the application is running, follow the on-screen prompts to add, update, and view your projects. You can add a new project by specifying:

Project Name

Status (In Progress, Completed)

Programming Language used

