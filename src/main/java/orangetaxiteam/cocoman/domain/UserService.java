package orangetaxiteam.cocoman.domain;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	public User create(String username, String rawPassword, int age, String gender) throws Exception {
		//username unique check
		
		User user = userRepository.findByUsername(username);
		if(user != null) {
			throw new Exception();//Exception 수정 필요
		}
		String password = new BCryptPasswordEncoder().encode(rawPassword);
		return userRepository.save(User.builder()
									.username(username)
									.password(password)
									.age(age)
									.gender(gender)
									.roles(Collections.singletonList("ROLE_USER"))
									.build());
	}
	
	public User singIn(String username, String password) throws Exception {
		User user = userRepository.findByUsername(username);
		if(!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
			throw new Exception(); //Exception 수정 필요
		}
		return user;
	}

	public Optional<User> findById(Long id){
		return userRepository.findById(id);
	}
	
	public User loadUserByUsername(String username) {
		return userRepository.findByUsername(username);//Exception 추가 필요
	}
}
