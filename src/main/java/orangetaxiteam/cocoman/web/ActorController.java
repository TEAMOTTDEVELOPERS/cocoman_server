package orangetaxiteam.cocoman.web;

import io.swagger.annotations.ApiOperation;
import orangetaxiteam.cocoman.application.ActorApplicationService;
import orangetaxiteam.cocoman.application.dto.ActorCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.ActorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/actor")
public class ActorController {
    private ActorApplicationService actorApplicationService;

    @Autowired
    public ActorController(ActorApplicationService actorApplicationService){
        this.actorApplicationService = actorApplicationService;
    }

    @GetMapping
    public @ResponseBody
    List<ActorDTO> findAll(){
        return actorApplicationService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody ActorDTO createActor(@RequestBody @Valid ActorCreateRequestDTO actorCreateRequestDTO){
        return actorApplicationService.create(actorCreateRequestDTO);
    }
}
