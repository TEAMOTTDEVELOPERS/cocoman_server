package orangetaxiteam.cocoman.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OttRepository extends JpaRepository<Ott, String> {
    Optional<Ott> findByName(String name);
}
