package orangetaxiteam.cocoman.domain;

import orangetaxiteam.cocoman.domain.exception.BadRequestException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {
    Optional<UserProfile> findByIdAndUser(String id, User user);

    default UserProfile findByIdAndUserOrElseThrow(String id, User user) {
        return this.findByIdAndUser(id, user)
                .orElseThrow(() -> new BadRequestException(String.format("profile '%s' of user '%s' does not exist", id, user.getId())));
    }
}
