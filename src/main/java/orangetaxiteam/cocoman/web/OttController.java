package orangetaxiteam.cocoman.web;

import orangetaxiteam.cocoman.application.OttApplicationService;
import orangetaxiteam.cocoman.application.dto.OttDTO;
import orangetaxiteam.cocoman.application.dto.RatePlanDTO;
import orangetaxiteam.cocoman.domain.Pagination;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


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

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public OttDTO findById(@PathVariable String id) {
        return ottApplicationService.findById(id);
    }

    @GetMapping(value = "/{id}/ratePlan")
    @ResponseStatus(value = HttpStatus.OK)
    public Pagination<RatePlanDTO> findRatePlanForOtt(
            @SortDefault(sort = "createdAt", direction = Sort.Direction.ASC) @PageableDefault(size = 15) final Pageable pageable,
            @PathVariable String id
    ) {
        return ottApplicationService.findRatePlanForOtt(pageable, id);
    }

    @GetMapping(value = "/ratePlan")
    @ResponseStatus(value = HttpStatus.OK)
    public Pagination<RatePlanDTO> findRatePlan(
            @SortDefault(sort = "createdAt", direction = Sort.Direction.ASC) @PageableDefault(size = 15) final Pageable pageable
    ) {
        return ottApplicationService.findRatePlan(pageable);
    }
}
