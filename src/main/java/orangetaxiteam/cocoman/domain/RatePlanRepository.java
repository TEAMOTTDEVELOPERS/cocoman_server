package orangetaxiteam.cocoman.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface RatePlanRepository extends JpaRepository<RatePlan, String> {
    @Query(value = "SELECT s FROM RatePlan s WHERE s.ott.id = :ott ORDER BY s.maxSimultaneous ASC")
    Page<RatePlan> findByOtt(String ott, Pageable pageable);
}
