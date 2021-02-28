package orangetaxiteam.cocoman.web;

import orangetaxiteam.cocoman.application.ContentsApplicationService;
import orangetaxiteam.cocoman.application.dto.ContentsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
