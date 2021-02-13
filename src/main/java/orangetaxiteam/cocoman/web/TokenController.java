package orangetaxiteam.cocoman.web;

import orangetaxiteam.cocoman.application.UserApplicationService;
import orangetaxiteam.cocoman.application.dto.UserDTO;
import orangetaxiteam.cocoman.application.dto.UserSignInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/token")
public class TokenController {
    private final UserApplicationService userApplicationService;

    public TokenController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public UserDTO signIn(@RequestBody UserSignInDTO userSignInDTO) {
        return this.userApplicationService.signIn(userSignInDTO);
    }
}
