package orangetaxiteam.cocoman.application.dto;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
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
	private Collection<? extends GrantedAuthority> roles;
	private int age;
	private String gender;
	private String phoneNum;
	private String profileImg;
	private String pushToken;
	
	public static UserDTO fromDAO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.id = user.getId();
		userDTO.username = user.getUsername();
		userDTO.password = user.getPassword();
		userDTO.roles = user.getAuthorities();
		userDTO.age = user.getAge();
		userDTO.gender = user.getGender();
		userDTO.phoneNum = user.getPhoneNum();
		userDTO.profileImg = user.getProfileImg();
		userDTO.pushToken = user.getPushToken();
		
		return userDTO;
	}
}
