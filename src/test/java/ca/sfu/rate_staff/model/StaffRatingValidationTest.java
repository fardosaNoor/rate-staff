package ca.sfu.rate_staff.model;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StaffRatingValidationTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    static void tearDown() {
        factory.close();
    }

    @Test
    void clarityScoreAboveTenShouldBeInvalid() {
        StaffRating rating = new StaffRating();
        rating.setClarityScore(11);

        var violations = validator.validateProperty(
                rating, "clarityScore");

        assertFalse(violations.isEmpty());
    }

    @Test
    void clarityScoreBelowOneShouldBeInvalid() {
        StaffRating rating = new StaffRating();
        rating.setClarityScore(0);

        var violations = validator.validateProperty(
                rating, "clarityScore");

        assertFalse(violations.isEmpty());
    }

    @Test
    void missingClarityScoreShouldBeInvalid() {
        StaffRating rating = new StaffRating();

        var violations = validator.validateProperty(
                rating, "clarityScore");

        assertFalse(violations.isEmpty());
    }

    @Test
    void blankNameShouldBeInvalid() {
        StaffRating rating = new StaffRating();
        rating.setName("");

        var violations = validator.validateProperty(
                rating, "name");

        assertFalse(violations.isEmpty());
    }

    @Test
    void invalidEmailShouldBeRejected() {
        StaffRating rating = new StaffRating();
        rating.setEmail("notanemail");

        var violations = validator.validateProperty(
                rating, "email");

        assertFalse(violations.isEmpty());
    }
    @Test
    void commentLongerThan500CharactersShouldBeInvalid() {
        StaffRating rating = new StaffRating();
        rating.setComment("a".repeat(501));

        var violations = validator.validateProperty(
                rating, "comment");

        assertFalse(violations.isEmpty());
    }
}
