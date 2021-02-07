package orangetaxiteam.cocoman.application.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import orangetaxiteam.cocoman.domain.Gender;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequestDTO {
	private String userId;
	private String nickName;
	private String password;
	private Integer age;
	private Gender gender;
	private String phoneNum;
	private String profileImg;
	private String pushToken;

	private String accessToken;
	private String provider;
}
