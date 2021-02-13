package orangetaxiteam.cocoman.web;

import orangetaxiteam.cocoman.application.KeywordApplicationService;
import orangetaxiteam.cocoman.application.dto.KeywordCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.KeywordDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/keywords")
public class KeywordController {
    private final KeywordApplicationService keywordApplicationService;

    public KeywordController(KeywordApplicationService keywordApplicationService) {
        this.keywordApplicationService = keywordApplicationService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public KeywordDTO createKeyword(@RequestBody KeywordCreateRequestDTO keywordCreateRequestDTO) {
        return this.keywordApplicationService.create(keywordCreateRequestDTO);
    }
}
