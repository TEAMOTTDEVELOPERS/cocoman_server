package orangetaxiteam.cocoman.web;

import orangetaxiteam.cocoman.application.UserApplicationService;
import orangetaxiteam.cocoman.application.dto.UserCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.UserDTO;
import orangetaxiteam.cocoman.application.dto.UserSignInDTO;
import orangetaxiteam.cocoman.application.dto.UserUpdateRequestDTO;
import orangetaxiteam.cocoman.application.dto.StarRatingDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PostMapping(value = "/signIn")
    @ResponseStatus(value = HttpStatus.OK)
    public UserDTO signIn(@RequestBody UserSignInDTO userSignInDTO) {
        return this.userApplicationService.signIn(userSignInDTO);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public UserDTO findById(
            @PathVariable String id,
            @AuthenticationPrincipal String currentUserId
    ) {
        if (!id.equals(currentUserId)) {
            throw new AccessDeniedException(String.format("no permission to id [%s] ", id));
        }
        return this.userApplicationService.findById(id);
    }

    @PatchMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public UserDTO updateUser(
            @PathVariable String id,
            @RequestBody UserUpdateRequestDTO userUpdateRequestDTO,
            @AuthenticationPrincipal String currentUserId
    ) {
        if (!id.equals(currentUserId)) {
            throw new AccessDeniedException(String.format("no permission to id [%s] ", id));
        }
        return this.userApplicationService.updateUser(id, userUpdateRequestDTO);
    }
  
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteUser(
            @PathVariable String id,
            @AuthenticationPrincipal String currentUserId
    ) {
        if(!id.equals(currentUserId)){
            throw new AccessDeniedException(String.format("no permission to id [%s] ", id));
        }
        this.userApplicationService.deleteUser(id);
    }

    @PostMapping(value = "/{userId}/validate")
    @ResponseStatus(value = HttpStatus.OK)
    public void validateUserId(@PathVariable String userId) {
        this.userApplicationService.validateUserId(userId);
    }

    @GetMapping(value = "/{id}/starRating")
    @ResponseStatus(value = HttpStatus.OK)
    public List<StarRatingDTO> findStarRating(
            @SortDefault(sort = "createdAt", direction = Sort.Direction.ASC) @PageableDefault(size = 15) final Pageable pageable,
            @PathVariable String id
    ) {
        return this.userApplicationService.findStarRatingByUserId(pageable, id);
    }
}

