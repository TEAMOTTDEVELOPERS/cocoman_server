package orangetaxiteam.cocoman.web;

import orangetaxiteam.cocoman.application.UserApplicationService;
import orangetaxiteam.cocoman.application.dto.UserCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.UserDTO;
import orangetaxiteam.cocoman.application.dto.UserUpdateRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public UserDTO findById(@PathVariable String id) {
        return this.userApplicationService.findById(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public UserDTO updateUser(@PathVariable String id, @RequestBody UserUpdateRequestDTO userUpdateRequestDTO) {
        return this.userApplicationService.updateUser(id, userUpdateRequestDTO);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteUser(@PathVariable String id) {
        this.userApplicationService.deleteUser(id);
    }

    @PostMapping(value = "/{userId}/validate")
    @ResponseStatus(value = HttpStatus.OK)
    public void existsByUserId(@PathVariable String userId) {
        this.userApplicationService.existsByUserId(userId);
    }

}

