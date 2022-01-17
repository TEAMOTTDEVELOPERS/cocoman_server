package orangetaxiteam.cocoman.web;

import orangetaxiteam.cocoman.application.OttApplicationService;
import orangetaxiteam.cocoman.application.dto.OttDTO;
import orangetaxiteam.cocoman.domain.Pagination;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ott")
public class OttController {
    private final OttApplicationService ottApplicationService;

    public OttController(OttApplicationService ottApplicationService) {
        this.ottApplicationService = ottApplicationService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Pagination<OttDTO> findAll(
            @SortDefault(sort = "createdAt", direction = Sort.Direction.ASC) @PageableDefault(size = 15) final Pageable pageable
    ) {
        return this.ottApplicationService.findAll(pageable);
    }

    @GetMapping(value = "/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    public OttDTO getOtt(@PathVariable String name){
        return ottApplicationService.getOtt(name);
    }
}
