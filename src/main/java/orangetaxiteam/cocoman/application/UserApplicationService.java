package orangetaxiteam.cocoman.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orangetaxiteam.cocoman.application.dto.UserCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.UserDTO;
import orangetaxiteam.cocoman.application.dto.UserSignInDTO;
import orangetaxiteam.cocoman.config.JwtTokenProvider;
import orangetaxiteam.cocoman.domain.User;
import orangetaxiteam.cocoman.domain.UserService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserApplicationService {

    private UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserApplicationService(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Transactional
    public UserDTO create(UserCreateRequestDTO userCreateRequestDTO) throws Exception { //Exception 수정 필요
        return UserDTO.from(userService.create(
                userCreateRequestDTO.getUserId(),
                userCreateRequestDTO.getNickName(),
                userCreateRequestDTO.getPassword(),
                userCreateRequestDTO.getAge(),
                userCreateRequestDTO.getGender(),
                userCreateRequestDTO.getPhoneNum(),
                userCreateRequestDTO.getProfileImg(),
                userCreateRequestDTO.getPushToken()));
    }

    public UserDTO signIn(UserSignInDTO userSignInDTO){
        User user = userService.signIn(userSignInDTO.getUserId(), userSignInDTO.getPassword());
        String jwtToken = jwtTokenProvider.createToken(user.getUserId());
        return UserDTO.from(
                userService.findByUserId(userSignInDTO.getUserId()),
                jwtToken);
    }
}
