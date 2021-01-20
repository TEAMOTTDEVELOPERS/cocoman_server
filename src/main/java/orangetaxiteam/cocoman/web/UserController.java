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

@RestController
@RequestMapping("/api/user")
public class UserController {

	private UserApplicationService userService;
	
	@PostMapping(value = "/createUser")
	@ApiOperation(value = "Create new user", tags = "User")
	public @ResponseBody
	Long createUser(@RequestBody UserDTO userDTO) {
		return userService.createUser(userDTO);
	}
}
