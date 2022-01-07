package orangetaxiteam.cocoman.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    @Query(value = "SELECT s FROM Review s WHERE s.contents = :contents ORDER BY s.createdAt DESC")
    List<Review> findRecentReviews(Pageable pageable, @Param("contents") Contents contents);
}
