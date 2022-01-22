package orangetaxiteam.cocoman.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequestDTO {
    private String nickName;
    private int age;
    private String gender;
    private String phoneNum;
    private String profileImg;
}
