package orangetaxiteam.cocoman.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import orangetaxiteam.cocoman.domain.Gender;
import orangetaxiteam.cocoman.domain.SocialProvider;

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

    private String accessToken;
    private SocialProvider provider;
}
