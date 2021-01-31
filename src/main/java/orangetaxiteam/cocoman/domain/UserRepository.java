package orangetaxiteam.cocoman.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, String>{
	User findByUserId(String userId);
}
