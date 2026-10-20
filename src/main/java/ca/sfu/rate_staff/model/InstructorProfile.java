package ca.sfu.rate_staff.model;

/**
 * Profile for course instructors (lecturers who are not professors).
 */
public class InstructorProfile extends StaffMemberProfile {

    @Override
    public String displayTitle() {
        return "Instructor";
    }
}
