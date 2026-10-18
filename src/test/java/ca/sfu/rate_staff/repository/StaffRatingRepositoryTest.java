
package ca.sfu.rate_staff.repository;

import ca.sfu.rate_staff.model.RoleType;
import ca.sfu.rate_staff.model.StaffRating;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class StaffRatingRepositoryTest {

    @Autowired
    private StaffRatingRepository repository;

    @Test
    void shouldSaveAndRetrieveRating() {
        StaffRating rating = new StaffRating();
        rating.setName("Persistence Test Staff");
        rating.setEmail("persistence-test@example.com");
        rating.setRoleType(RoleType.TA);
        rating.setClarityScore(8);
        rating.setNicenessScore(9);
        rating.setKnowledgeableScore(7);
        rating.setComment("Testing database persistence");

        StaffRating saved = repository.save(rating);

        assertNotNull(saved.getId());

        StaffRating retrieved = repository.findById(saved.getId())
                .orElseThrow();

        assertEquals("Persistence Test Staff", retrieved.getName());
        assertEquals("persistence-test@example.com", retrieved.getEmail());

        repository.deleteById(saved.getId());
    }


    @Test
    void shouldDeleteRating() {
        StaffRating rating = new StaffRating();
        rating.setName("Delete Test Staff");
        rating.setEmail("delete-test@example.com");
        rating.setRoleType(RoleType.TA);
        rating.setClarityScore(8);
        rating.setNicenessScore(9);
        rating.setKnowledgeableScore(7);

        StaffRating saved = repository.save(rating);
        Long id = saved.getId();

        repository.deleteById(id);

        assertFalse(repository.existsById(id));
    }

}
