package orangetaxiteam.cocoman.web;

import io.swagger.annotations.ApiOperation;
import orangetaxiteam.cocoman.application.ContentsApplicationService;
import orangetaxiteam.cocoman.application.dto.ContentsCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.ContentsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contents")
public class ContentsController {
    @Autowired
    ContentsApplicationService contentsApplicationService;

    @PostMapping(value = "/createContents")
    @ApiOperation(value = "Create new contents", tags = "Contents")
    public @ResponseBody
    ContentsDTO createContents(@RequestBody ContentsCreateRequestDTO contentsCreateRequestDTO){
        return contentsApplicationService.create(contentsCreateRequestDTO);
    }

    @GetMapping(value = "/findAll")
    @ApiOperation(value = "Get all contents", tags = "Contents")
    public @ResponseBody
    List<ContentsDTO> findAllContents(){
        return contentsApplicationService.findAll();
    }

}
