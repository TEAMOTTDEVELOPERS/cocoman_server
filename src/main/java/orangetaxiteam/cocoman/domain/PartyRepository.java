package orangetaxiteam.cocoman.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartyRepository extends JpaRepository<Party, String> {
    Optional<Party> findByPartyName(String name);
}
