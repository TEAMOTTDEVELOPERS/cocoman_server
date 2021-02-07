package orangetaxiteam.cocoman.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import orangetaxiteam.cocoman.application.UserApplicationService;
import orangetaxiteam.cocoman.application.dto.UserCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.UserDTO;
import orangetaxiteam.cocoman.application.dto.UserSignInDTO;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	private UserApplicationService userApplicationService;
	
	@Autowired
	public UserController(UserApplicationService userApplicationService) {
		this.userApplicationService = userApplicationService;
	}
	
	@PostMapping(value = "/signUp")
	public @ResponseBody
	UserDTO signUp(@RequestBody UserCreateRequestDTO userCreateRequestDTO) throws Exception { //Exception 수정 필요
		return userApplicationService.create(userCreateRequestDTO);
	}
	
	@PostMapping(value = "/signIn")
	public @ResponseBody
	UserDTO signIn(@RequestBody UserSignInDTO userSignInDTO) throws Exception { //Exception 수정 필요
		return userApplicationService.signIn(userSignInDTO);
	}
}

