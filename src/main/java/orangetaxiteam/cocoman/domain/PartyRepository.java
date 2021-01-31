package orangetaxiteam.cocoman.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartyRepository extends JpaRepository<Party, String> {
    Optional<Party> findByPartyName(String name);
}
