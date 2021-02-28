package orangetaxiteam.cocoman.web;

import orangetaxiteam.cocoman.application.GenreApplicationService;
import orangetaxiteam.cocoman.application.dto.GenreDTO;
import orangetaxiteam.cocoman.domain.Pagination;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/genres")
public class GenreController {
    private final GenreApplicationService genreApplicationService;

    public GenreController(GenreApplicationService genreApplicationService) {
        this.genreApplicationService = genreApplicationService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Pagination<GenreDTO> findAll(
            @SortDefault(sort = "createdAt", direction = Sort.Direction.ASC) @PageableDefault(size = 15) final Pageable pageable
    ) {
        return this.genreApplicationService.findAll(pageable);
    }
}
