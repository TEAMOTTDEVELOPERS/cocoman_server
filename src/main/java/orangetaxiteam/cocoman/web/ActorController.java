package orangetaxiteam.cocoman.web;

import io.swagger.annotations.ApiOperation;
import orangetaxiteam.cocoman.application.ActorApplicationService;
import orangetaxiteam.cocoman.application.dto.ActorCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.ActorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/actor")
public class ActorController {
    private ActorApplicationService actorApplicationService;

    @Autowired
    public ActorController(ActorApplicationService actorApplicationService){
        this.actorApplicationService = actorApplicationService;
    }

    @PostMapping(value = "/createActor")
    @ApiOperation(value = "Create new actor", tags = "Actor")
    public @ResponseBody
    ActorDTO createContents(@RequestBody @Valid ActorCreateRequestDTO actorCreateRequestDTO){
        return actorApplicationService.create(actorCreateRequestDTO);
    }
}
