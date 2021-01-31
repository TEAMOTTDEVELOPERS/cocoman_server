package orangetaxiteam.cocoman.application.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequestDTO {
	private String userId;
	private String nickName;
	private String password;
	private Integer age;
	private String gender;
	private String phoneNum;
	private String profileImg;
	private String pushToken;

}
