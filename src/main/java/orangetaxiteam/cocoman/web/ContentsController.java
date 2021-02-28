package orangetaxiteam.cocoman.web;

import orangetaxiteam.cocoman.application.ContentsApplicationService;
import orangetaxiteam.cocoman.application.dto.ContentsDetailDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ContentsDetailDTO findById(@PathVariable String id) {
        return this.contentsApplicationService.findById(id);
    }

    @PostMapping(value = "/{contentsId}/search-histories")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void loggingSearch(
            @PathVariable String contentId,
            @RequestParam(value = "keyword", required = false) String keyword,
            @AuthenticationPrincipal String currentUserId
    ) {
        this.contentsApplicationService.createSearchHistory(contentId, keyword, currentUserId);
    }

}
