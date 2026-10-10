# Testing

## How to Run the Tests

To run all the tests, open the terminal in the project folder and run:

./mvnw test

You can also run each test file separately:


./mvnw -Dtest=StaffRatingValidationTest test
./mvnw -Dtest=StaffRatingControllerTest test
./mvnw -Dtest=StaffRatingRepositoryTest test

## Validation Tests

I wrote tests to check that the validation rules work properly. They check that clarity scores cannot be below 1 or above 10, that a score cannot be missing, that the name cannot be empty, that the email has a valid format, and that comments cannot be longer than 500 characters.

## Controller Tests

I tested the main page to make sure it loads properly and gets the staff ratings. I also tested submitting a valid rating to make sure it saves and redirects to the home page. Another test checks that submitting a blank name shows a validation error instead of saving the rating.

The controller tests use a mock repository, so they do not need to access the actual database.

## Database Tests

I tested saving a staff rating and retrieving it from PostgreSQL to check that the information was saved correctly. I also tested deleting a rating and checking that it was removed.

These tests use my local PostgreSQL database, and the test records are deleted after the tests finish.

## Test Setup

The project uses Spring Boot, JUnit, and PostgreSQL for testing. PostgreSQL needs to be running, and the database settings in `application.properties` need to be correct before running the database tests.
