package orangetaxiteam.cocoman.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import orangetaxiteam.cocoman.application.UserApplicationService;
import orangetaxiteam.cocoman.application.dto.UserCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.UserDTO;
import orangetaxiteam.cocoman.application.dto.UserUpdateRequestDTO;

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
	

	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "access token", required = true, dataType="String", paramType="header")
	})
	@GetMapping(value = "/{id}")
	public @ResponseBody
	UserDTO findById(@PathVariable String id) {
		return userApplicationService.findById(id);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "access token", required = true, dataType="String", paramType="header")
	})
	@PutMapping(value = "/{id}")
	@ApiOperation(value = "Update User Info", tags = "User")
	public @ResponseBody
	UserDTO updateUser(@PathVariable String id, @RequestBody UserUpdateRequestDTO userUpdateRequestDTO) {
		return userApplicationService.updateUser(id, userUpdateRequestDTO);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "access token", required = true, dataType="String", paramType="header")
	})
	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "Delete User Info", tags = "User")
	public @ResponseBody
	void deleteUser(@PathVariable String id) {
		userApplicationService.deleteUser(id);
	}
}

