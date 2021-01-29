package orangetaxiteam.cocoman.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orangetaxiteam.cocoman.application.dto.UserCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.UserDTO;
import orangetaxiteam.cocoman.application.dto.UserSignInDTO;
import orangetaxiteam.cocoman.config.JwtTokenProvider;
import orangetaxiteam.cocoman.domain.User;
import orangetaxiteam.cocoman.domain.UserService;

@Service
public class UserApplicationService {
	
	private UserService userService;
	private final JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	public UserApplicationService( UserService userService, JwtTokenProvider jwtTokenProvider) {
		this.userService = userService;
		this.jwtTokenProvider = jwtTokenProvider;
	}
	
	public UserDTO create(UserCreateRequestDTO userCreateRequestDTO) throws Exception { //Exception 수정 필요
		return UserDTO.fromDAO(userService.create(userCreateRequestDTO.getUsername(), userCreateRequestDTO.getPassword(), userCreateRequestDTO.getAge(), userCreateRequestDTO.getGender()));
	}
	
	public UserDTO signIn(UserSignInDTO userSignInDTO) throws Exception { //Exception 수정 필요
		User user = userService.singIn(userSignInDTO.getUsername(), userSignInDTO.getPassword());
		String jwtToken = jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
		return UserDTO.fromDAO(userService.loadUserByUsername(userSignInDTO.getUsername()), jwtToken);
	}
	
	public UserDTO findByUsername(String username) {
		return UserDTO.fromDAO(userService.loadUserByUsername(username));
	}
}
