package orangetaxiteam.cocoman.web;

import io.swagger.annotations.ApiOperation;
import orangetaxiteam.cocoman.application.GenreApplicationService;
import orangetaxiteam.cocoman.application.dto.GenreCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.GenreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/genre")
public class GenreController {
    private GenreApplicationService genreApplicationService;

    @Autowired
    public GenreController(GenreApplicationService genreApplicationService){
        this.genreApplicationService = genreApplicationService;
    }

    @PostMapping
    public @ResponseBody
    GenreDTO createGenre(@RequestBody @Valid GenreCreateRequestDTO genreCreateRequestDTO){
        return genreApplicationService.create(genreCreateRequestDTO);
    }
}
