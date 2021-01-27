package orangetaxiteam.cocoman.web;

import io.swagger.annotations.ApiOperation;
import orangetaxiteam.cocoman.application.KeywordApplicationService;
import orangetaxiteam.cocoman.application.dto.KeywordCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.KeywordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/keyword")
public class KeywordController {
    private KeywordApplicationService keywordApplicationService;

    @Autowired
    public KeywordController(KeywordApplicationService keywordApplicationService){
        this.keywordApplicationService = keywordApplicationService;
    }

    @PostMapping(value = "/createKeyword")
    @ApiOperation(value = "Create new keyword", tags = "Keyword")
    public @ResponseBody
    KeywordDTO createContents(@RequestBody @Valid KeywordCreateRequestDTO keywordCreateRequestDTO){
        return keywordApplicationService.create(keywordCreateRequestDTO);
    }
}
