package orangetaxiteam.cocoman.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import orangetaxiteam.cocoman.application.UserApplicationService;
import orangetaxiteam.cocoman.application.dto.UserDTO;
import orangetaxiteam.cocoman.application.dto.UserSignInDTO;

@RestController
@RequestMapping("/api/token")
public class TokenController {
	
private UserApplicationService userApplicationService;
	
	@Autowired
	public TokenController(UserApplicationService userApplicationService) {
		this.userApplicationService = userApplicationService;
	}

	@PostMapping
	@ApiOperation(value = "Sign in with default account", tags = "User")
	public @ResponseBody
	UserDTO signIn(@RequestBody UserSignInDTO userSignInDTO) throws Exception { //Exception 수정 필요
		return userApplicationService.signIn(userSignInDTO);
	}
}
