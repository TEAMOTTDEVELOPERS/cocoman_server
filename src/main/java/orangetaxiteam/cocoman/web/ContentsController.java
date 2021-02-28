package orangetaxiteam.cocoman.web;

import orangetaxiteam.cocoman.application.ContentsApplicationService;
import orangetaxiteam.cocoman.application.dto.ContentsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/contents")
public class ContentsController {
    private final ContentsApplicationService contentsApplicationService;

    public ContentsController(ContentsApplicationService contentsApplicationService) {
        this.contentsApplicationService = contentsApplicationService;
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ContentsDTO findById(@PathVariable String id) {
        return this.contentsApplicationService.findById(id);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<ContentsDTO> findAllContents() {
        return this.contentsApplicationService.findAll();
    }

    @PostMapping(value = "/{id}/search-history")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void loggingSearch(
            @PathVariable String id,
            @RequestParam(value = "keyword", required = false) String keyword,
            @AuthenticationPrincipal String currentUserId
    ) {
        this.contentsApplicationService.createSearchHistory(id, keyword, currentUserId);
    }

}
