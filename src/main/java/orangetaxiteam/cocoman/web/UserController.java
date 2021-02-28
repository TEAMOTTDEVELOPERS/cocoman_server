package orangetaxiteam.cocoman.web;

import orangetaxiteam.cocoman.application.UserApplicationService;
import orangetaxiteam.cocoman.application.dto.UserCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.UserDTO;
import orangetaxiteam.cocoman.application.dto.UserUpdateRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserDTO signUp(@RequestBody UserCreateRequestDTO userCreateRequestDTO) {
        return this.userApplicationService.create(userCreateRequestDTO);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public UserDTO findById(
            @PathVariable String id,
            @AuthenticationPrincipal String currentUserId
    ) {
        if (!id.equals(currentUserId)) throw new AccessDeniedException(String.format("no permission to id [%s] ", id));
        return this.userApplicationService.findById(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public UserDTO updateUser(@PathVariable String id,
                              @RequestBody UserUpdateRequestDTO userUpdateRequestDTO,
                              @AuthenticationPrincipal String currentUserId
    ) {
        if (!id.equals(currentUserId)) throw new AccessDeniedException(String.format("no permission to id [%s] ", id));
        return this.userApplicationService.updateUser(id, userUpdateRequestDTO);
    }
}

