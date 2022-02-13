package orangetaxiteam.cocoman.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequestDTO {
    @NotEmpty
    private String userId;
    private String nickName;
    private String password;

    @NotNull
    @Digits(integer = 3, fraction = 0)
    private Integer age;

    @NotEmpty
    private String gender;
    private String phoneNum;
    private String profileImg;
    private String pushToken;

    private String accessToken;

    @NotEmpty
    private String provider;
}
