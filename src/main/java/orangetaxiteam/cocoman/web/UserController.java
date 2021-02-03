package orangetaxiteam.cocoman.web;

import io.swagger.annotations.ApiOperation;
import orangetaxiteam.cocoman.application.UserApplicationService;
import orangetaxiteam.cocoman.application.dto.CreateProfileRequest;
import orangetaxiteam.cocoman.application.dto.ProfileDTO;
import orangetaxiteam.cocoman.application.dto.UserCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.UserDTO;
import orangetaxiteam.cocoman.application.dto.UserSignInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @PostMapping(value = "/signUp")
    @ApiOperation(value = "Create new user", tags = "User")
    public @ResponseBody
    UserDTO signUp(@RequestBody UserCreateRequestDTO userCreateRequestDTO) throws Exception { //Exception 수정 필요
        return this.userApplicationService.create(userCreateRequestDTO);
    }

    @PostMapping(value = "/signIn")
    @ApiOperation(value = "Signin", tags = "User")
    public @ResponseBody
    UserDTO signIn(@RequestBody UserSignInDTO userSignInDTO) throws Exception { //Exception 수정 필요
        return this.userApplicationService.signIn(userSignInDTO);
    }

    @PostMapping(value = "/{userId}/profiles")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ProfileDTO createProfile(@PathVariable String userId, @RequestBody CreateProfileRequest request) {
        return this.userApplicationService.createUserProfile(userId, request);
    }

    @GetMapping(value = "/{userId}/profiles/{profileId}")
    @ResponseStatus(value = HttpStatus.OK)
    public ProfileDTO getProfile(@PathVariable String userId, @PathVariable String profileId) {
        return this.userApplicationService.getUserProfile(userId, profileId);
    }
}

