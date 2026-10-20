package ca.sfu.rate_staff.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class StaffMemberProfileTest {

    private StaffRating ratingWithRole(RoleType role) {
        StaffRating rating = new StaffRating();
        rating.setRoleType(role);
        return rating;
    }

    @Test
    void taShouldDisplayAsTeachingAssistant() {
        StaffMemberProfile profile = ratingWithRole(RoleType.TA).getProfile();
        assertInstanceOf(TeachingAssistantProfile.class, profile);
        assertEquals("Teaching Assistant", profile.displayTitle());
    }

    @Test
    void profShouldDisplayAsProfessor() {
        StaffMemberProfile profile = ratingWithRole(RoleType.PROF).getProfile();
        assertInstanceOf(ProfessorProfile.class, profile);
        assertEquals("Professor", profile.displayTitle());
    }

    @Test
    void instructorShouldDisplayAsInstructor() {
        StaffMemberProfile profile = ratingWithRole(RoleType.INSTRUCTOR).getProfile();
        assertInstanceOf(InstructorProfile.class, profile);
        assertEquals("Instructor", profile.displayTitle());
    }
}
