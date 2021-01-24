package orangetaxiteam.cocoman.web;

import io.swagger.annotations.ApiOperation;
import orangetaxiteam.cocoman.application.ContentsApplicationService;
import orangetaxiteam.cocoman.application.dto.ContentsCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.ContentsDTO;
import orangetaxiteam.cocoman.application.dto.ContentsFindByTitleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/contents")
public class ContentsController {
    private ContentsApplicationService contentsApplicationService;

    @Autowired
    public ContentsController(ContentsApplicationService contentsApplicationService){
        this.contentsApplicationService = contentsApplicationService;
    }

    @PostMapping(value = "/createContents")
    @ApiOperation(value = "Create new contents", tags = "Contents")
    public @ResponseBody
    ContentsDTO createContents(@RequestBody @Valid ContentsCreateRequestDTO contentsCreateRequestDTO){
        return contentsApplicationService.create(contentsCreateRequestDTO);
    }

    @PostMapping(value = "/findByTitle")
    @ApiOperation(value = "Find contents by title", tags = "Contents")
    public @ResponseBody
    List<ContentsDTO> findByTitle(@RequestBody @Valid ContentsFindByTitleDTO contentsFindByTitleDTO){
        return contentsApplicationService.findByTitle(contentsFindByTitleDTO);
    }

    @GetMapping(value = "/findAll")
    @ApiOperation(value = "Get all contents", tags = "Contents")
    public @ResponseBody
    List<ContentsDTO> findAllContents(){
        return contentsApplicationService.findAll();
    }

}
