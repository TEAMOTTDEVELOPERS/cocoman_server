package orangetaxiteam.cocoman.web;

import orangetaxiteam.cocoman.application.DirectorApplicationService;
import orangetaxiteam.cocoman.application.dto.DirectorCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.DirectorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/directors")
public class DirectorController {
    private final DirectorApplicationService directorApplicationService;

    public DirectorController(DirectorApplicationService directorApplicationService) {
        this.directorApplicationService = directorApplicationService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public DirectorDTO createDirector(@RequestBody DirectorCreateRequestDTO directorCreateRequestDTO) {
        return this.directorApplicationService.create(directorCreateRequestDTO);
    }
}
