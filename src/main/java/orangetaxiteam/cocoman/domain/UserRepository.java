package orangetaxiteam.cocoman.domain;

import orangetaxiteam.cocoman.domain.exceptions.BadRequestException;
import orangetaxiteam.cocoman.domain.exceptions.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

    default User findByEmailOrElseThrow(String email) {
        return this.findByEmail(email).orElseThrow(
                () -> new BadRequestException(ErrorCode.ROW_DOES_NOT_EXIST, "invalid user email")
        );
    }

    Optional<User> findBySocialId(String socialId);

    default User findBySocialIdOrElseThrow(String socialId) {
        return this.findBySocialId(socialId).orElseThrow(
                () -> new BadRequestException(ErrorCode.ROW_DOES_NOT_EXIST, "Invalid social Id")
        );
    }
}
