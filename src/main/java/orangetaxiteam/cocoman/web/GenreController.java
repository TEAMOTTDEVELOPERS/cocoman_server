package orangetaxiteam.cocoman.web;

import orangetaxiteam.cocoman.application.GenreApplicationService;
import orangetaxiteam.cocoman.application.dto.GenreDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/genres")
public class GenreController {
    private final GenreApplicationService genreApplicationService;

    public GenreController(GenreApplicationService genreApplicationService) {
        this.genreApplicationService = genreApplicationService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GenreDTO> findAll() {
        return this.genreApplicationService.findAll();
    }
}
