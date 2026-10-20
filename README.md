# Rate CS Teaching Staff

## Description

Rate CS Teaching Staff is a web application where students can rate their teaching assistants, professors, and instructors. Students can give ratings for clarity, niceness, and knowledge, and leave an optional comment.

The application was built using Java and Spring Boot, with PostgreSQL used to store the ratings.

## Features

- Add a staff rating
- View all ratings, with the average score for each staff member
- View the details of a rating
- Edit a rating
- Delete a rating, with a confirmation step
- Search staff by name and filter by role on the home page
- Validate form inputs
- Prevent duplicate email addresses
- Show error messages when the input is invalid

## Validation Rules

- Name is required
- Email is required, must be a valid email format, and must be unique
- Role is required
- Clarity, niceness, and knowledge scores must be whole numbers from 1 to 10
- Comment is optional, but cannot be longer than 500 characters


## Design

Each role has its own profile class that extends the abstract class `StaffMemberProfile`. Each profile class overrides the `displayTitle()` method, so a TA shows as "Teaching Assistant", a professor shows as "Professor", and an instructor shows as "Instructor". `StaffRating.getProfile()` returns the right profile for the rating's role, and the title is shown on the home page and the details page.

## Technologies

- Java 21
- Spring Boot
- Thymeleaf
- PostgreSQL
- HTML and CSS
- Maven
- JUnit and Mockito for testing
- Docker and Render for deployment

## How to Run Locally

1. Make sure Java 21 and PostgreSQL are installed and PostgreSQL is running.
2. Create a PostgreSQL database named `rate_staff`.
3. By default, the app connects to `localhost:5432/rate_staff` with the username `fardosanoor` and no password (my local setup). If your setup is different, set these environment variables before running instead of changing the properties file:

   export DB_URL=jdbc:postgresql://localhost:5432/rate_staff
   export DB_USERNAME=your_username
   export DB_PASSWORD=your_password

   On Windows PowerShell, use `$env:DB_USERNAME="your_username"` instead of `export`.
4. In the project directory, run:

   ./mvnw spring-boot:run


5. Open http://localhost:8080 in your browser.

The database table is created automatically when the app starts.


## How to Deploy

Render URL: https://rate-staff-2.onrender.com/

The app is deployed on Render using the `Dockerfile` in this project.

1. In Render, create a new **PostgreSQL** database.
2. Create a new **Web Service**, connect this GitHub repository, and choose **Docker** as the runtime.
3. In the web service's **Environment** tab, add these variables using the values from the database's Connections section:
   - `DB_URL` = `jdbc:postgresql://<hostname>:5432/<database>`
   - `DB_USERNAME` = the database username
   - `DB_PASSWORD` = the database password
4. Save the changes. Render deploys the app automatically every time I push to GitHub.

No passwords are stored in the code. The `application.properties` file reads the database settings from these environment variables, and Render sets the port automatically.

