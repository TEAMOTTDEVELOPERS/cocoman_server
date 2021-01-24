package orangetaxiteam.cocoman.domain;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import orangetaxiteam.cocoman.application.dto.UserCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.UserSignInDTO;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
	
	private final UserRepository userRepository;

	
	public User create(UserCreateRequestDTO userCreateRequestDTO) {
		User user = userCreateRequestDTO.toEntity();
		return userRepository.save(user);
	}
	
	public User singIn(UserSignInDTO userSignInDTO) throws Exception {
		User user = userRepository.findByUsername(userSignInDTO.getUsername());
		if(!new BCryptPasswordEncoder().matches(userSignInDTO.getPassword(), user.getPassword())) {
			throw new Exception(); //Exception 수정 필요
		}
		return user;
	}
	
	public User loadUserByUsername(String userPk) {
		return userRepository.findById(Long.valueOf(userPk)).orElseThrow(() -> new UsernameNotFoundException(""));//Exception 수정 필요
	}
}
