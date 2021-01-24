package orangetaxiteam.cocoman.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import orangetaxiteam.cocoman.application.dto.UserCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.UserDTO;
import orangetaxiteam.cocoman.application.dto.UserSignInDTO;
import orangetaxiteam.cocoman.domain.UserService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserApplicationService {
	
	@Autowired
	private UserService userService;
	
	public UserDTO create(UserCreateRequestDTO userCreateRequestDTO) {
		return UserDTO.fromDAO(userService.create(userCreateRequestDTO));
	}
	
	public UserDTO signIn(UserSignInDTO userSignInDTO) throws Exception { //Exception 수정 필요
		return UserDTO.fromDAO(userService.singIn(userSignInDTO));
	}
}
