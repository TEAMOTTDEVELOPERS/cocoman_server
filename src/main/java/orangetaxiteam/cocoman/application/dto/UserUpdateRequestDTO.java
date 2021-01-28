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
public class UserUpdateRequestDTO {

	private List<String> roles;
	private int age;
	private String gender;
	private String phoneNum;
	private String profileImg;
	private String pushToken;

}
