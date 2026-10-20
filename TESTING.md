# Testing

## How to Run the Tests

To run all the tests, open the terminal in the project folder and run:

./mvnw test

You can also run each test file separately:


./mvnw -Dtest=StaffRatingValidationTest test
./mvnw -Dtest=StaffRatingControllerTest test
./mvnw -Dtest=StaffRatingRepositoryTest test

## Validation Tests

I wrote tests to check that the validation rules work properly. They check that clarity scores cannot be below 1 or above 10, that a score cannot be missing, that the name cannot be empty or longer than 100 characters, that the email has a valid format, and that comments cannot be longer than 500 characters. These are plain unit tests that use the Jakarta validator directly, so they do not start Spring or need a database.

## Controller Tests

I tested the main page to make sure it loads properly and gets the staff ratings. I also tested submitting a valid rating to make sure it saves and redirects to the home page. Another test checks that submitting a blank name shows a validation error instead of saving the rating.

## Database Tests

I tested saving a staff rating and retrieving it from PostgreSQL to check that the information was saved correctly. I also tested deleting a rating and checking that it was removed. The test records are deleted after the tests finish.

## Design Tests

I tested the role-based profiles to check that each role type returns the correct profile class and title: a TA displays as "Teaching Assistant", a professor as "Professor", and an instructor as "Instructor". This confirms that the polymorphic `displayTitle()` method works for every role.

## Why I Chose These Test Types

I used a mix of slice tests and full integration tests, depending on what each test needed to check.

For the controller tests, I used `@WebMvcTest` with a mocked repository (`@MockitoBean`). They check the controller's behaviour on its own: which page is returned, whether validation errors appear, and whether the repository's save method is called.

For the database tests, I used `@SpringBootTest` so the tests run against a real PostgreSQL database. I chose this over an in-memory database because the deployed app uses PostgreSQL, so testing against the same database gives more confidence that saving and deleting work the same way in production.

## Test Setup

The project uses Spring Boot, JUnit, and Mockito for testing. The validation, design and controller tests run without a database. The database tests and the application context test need PostgreSQL to be running locally, with the connection set through the `DB_URL`, `DB_USERNAME` and `DB_PASSWORD` environment variables (or the defaults in `application.properties`).