package orangetaxiteam.cocoman.application.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import orangetaxiteam.cocoman.domain.Gender;
import orangetaxiteam.cocoman.domain.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String id;
    private String nickName;
    private int age;
    private Gender gender;
    private String phoneNum;
    private String profileImg;
    private String pushToken;
    private String jwtToken;

    public static UserDTO from(User user) {
        return from(user, "");
    }

    public static UserDTO from(User user, String jwtToken) {
        UserDTO userDTO = new UserDTO();
        userDTO.id = user.getId();
        userDTO.nickName = user.getNickName();
        userDTO.age = user.getAge();
        userDTO.gender = user.getGender();
        userDTO.phoneNum = user.getPhoneNum();
        userDTO.profileImg = user.getProfileImg();
        userDTO.pushToken = user.getPushToken();
        userDTO.jwtToken = jwtToken;
        return userDTO;
    }

}
