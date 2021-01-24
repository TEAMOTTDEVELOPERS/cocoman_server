package orangetaxiteam.cocoman.application.dto;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import orangetaxiteam.cocoman.domain.User;

@Data
@AllArgsConstructor
public class UserCreateRequestDTO {
	
	@Autowired
	private final BCryptPasswordEncoder passwordEncoder;
	
	private String username;
	private String password;
	private List<String> roles;
	private int age;
	private String gender;
	private String profileImg;
	private String pushToken;
	
	public User toEntity() {
		return User.builder()
				.username(username)
				.password(passwordEncoder.encode(password))
				.roles(Collections.singletonList("ROLE_USER"))
				.age(age)
				.gender(gender)
				.profileImg(profileImg)
				.pushToken(pushToken)
				.build();
	}
}
