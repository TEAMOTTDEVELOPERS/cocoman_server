package orangetaxiteam.cocoman.web;

import io.swagger.annotations.ApiOperation;
import orangetaxiteam.cocoman.application.DirectorApplicationService;
import orangetaxiteam.cocoman.application.dto.DirectorCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.DirectorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/director")
public class DirectorController {
    private DirectorApplicationService directorApplicationService;

    @Autowired
    public DirectorController(DirectorApplicationService directorApplicationService){
        this.directorApplicationService = directorApplicationService;
    }

    @PostMapping(value = "/createDirector")
    @ApiOperation(value = "Create new director", tags = "Director")
    public @ResponseBody
    DirectorDTO createContents(@RequestBody @Valid DirectorCreateRequestDTO directorCreateRequestDTO){
        return directorApplicationService.create(directorCreateRequestDTO);
    }
}
