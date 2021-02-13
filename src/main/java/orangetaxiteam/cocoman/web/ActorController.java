package orangetaxiteam.cocoman.web;

import orangetaxiteam.cocoman.application.ActorApplicationService;
import orangetaxiteam.cocoman.application.dto.ActorCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.ActorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/actors")
public class ActorController {
    private final ActorApplicationService actorApplicationService;

    public ActorController(ActorApplicationService actorApplicationService) {
        this.actorApplicationService = actorApplicationService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<ActorDTO> findAll() {
        return this.actorApplicationService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActorDTO createActor(@RequestBody ActorCreateRequestDTO actorCreateRequestDTO) {
        return this.actorApplicationService.create(actorCreateRequestDTO);
    }
}
