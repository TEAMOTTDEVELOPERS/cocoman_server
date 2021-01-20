package orangetaxiteam.cocoman.application.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import orangetaxiteam.cocoman.domain.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private Long id;
	private String username;
	private String password;
	private String role;
	private int age;
	private String gender;
	private String phonenum;
	private String profileImg;
	private String pushtoken;
	
	public User toEntity() {
		return User.builder()
				.id(id)
				.username(username)
				.password(new BCryptPasswordEncoder().encode(password))
				.role(role)
				.age(age)
				.gender(gender)
				.phonenum(phonenum)
				.profileImg(profileImg)
				.pushtoken(pushtoken)
				.build();
	}
}
