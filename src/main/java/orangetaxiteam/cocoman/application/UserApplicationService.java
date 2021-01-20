package orangetaxiteam.cocoman.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import orangetaxiteam.cocoman.application.dto.UserDTO;
import orangetaxiteam.cocoman.domain.User;
import orangetaxiteam.cocoman.domain.UserRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserApplicationService {

	private final UserRepository userRepository;
	
	@Transactional
	public Long createUser(UserDTO dto) {
		User user = dto.toEntity();
		userRepository.save(user);
		return user.getId();
	}
}
