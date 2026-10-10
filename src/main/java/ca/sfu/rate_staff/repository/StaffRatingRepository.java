package ca.sfu.rate_staff.repository;

import ca.sfu.rate_staff.model.StaffRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRatingRepository
        extends JpaRepository<StaffRating, Long> {
}
