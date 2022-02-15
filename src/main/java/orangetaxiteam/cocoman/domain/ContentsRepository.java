package orangetaxiteam.cocoman.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ContentsRepository extends JpaRepository<Contents, String> {
    @Query(value = "SELECT new orangetaxiteam.cocoman.domain.WeekTopContents (s.contents, AVG(s.rating)) " +
            "FROM StarRating s " +
            "WHERE s.createdAt > :threshold " +
            "GROUP BY s.contents " +
            "ORDER BY AVG(s.rating) DESC")
    List<WeekTopContents> weekTopContents(Pageable pageable, @Param("threshold") LocalDateTime localDateTime);

}
