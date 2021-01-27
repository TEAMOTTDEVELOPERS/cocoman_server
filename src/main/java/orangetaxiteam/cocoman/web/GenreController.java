package orangetaxiteam.cocoman.web;

import io.swagger.annotations.ApiOperation;
import orangetaxiteam.cocoman.application.GenreApplicationService;
import orangetaxiteam.cocoman.application.dto.GenreCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.GenreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/genre")
public class GenreController {
    private GenreApplicationService genreApplicationService;

    @Autowired
    public GenreController(GenreApplicationService genreApplicationService){
        this.genreApplicationService = genreApplicationService;
    }

    @PostMapping(value = "/createGenre")
    @ApiOperation(value = "Create new genre", tags = "Genre")
    public @ResponseBody
    GenreDTO createContents(@RequestBody @Valid GenreCreateRequestDTO genreCreateRequestDTO){
        return genreApplicationService.create(genreCreateRequestDTO);
    }
}
