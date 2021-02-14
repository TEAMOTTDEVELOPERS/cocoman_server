package orangetaxiteam.cocoman.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StarRatingRepository extends JpaRepository<StarRating, String> {
    Optional<StarRating> findByUserAndContents(User user, Contents contents);

    boolean existsByUserAndContents(User user, Contents contents);
}
