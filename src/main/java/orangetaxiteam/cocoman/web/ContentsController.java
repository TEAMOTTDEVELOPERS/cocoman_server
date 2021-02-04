package orangetaxiteam.cocoman.web;

import io.swagger.annotations.ApiOperation;
import orangetaxiteam.cocoman.application.ContentsApplicationService;
import orangetaxiteam.cocoman.application.dto.ContentsCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.ContentsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/contents")
public class ContentsController {
    private ContentsApplicationService contentsApplicationService;

    @Autowired
    public ContentsController(ContentsApplicationService contentsApplicationService){
        this.contentsApplicationService = contentsApplicationService;
    }

    @PostMapping
    public @ResponseBody
    ContentsDTO createContents(@RequestBody @Valid ContentsCreateRequestDTO contentsCreateRequestDTO){
        return contentsApplicationService.create(contentsCreateRequestDTO);
    }

    @GetMapping(value = "/{id}")
    public @ResponseBody
    ContentsDTO findById(@PathVariable String id){
        return contentsApplicationService.findById(id);
    }

    @GetMapping
    public @ResponseBody
    List<ContentsDTO> findAllContents(){
        return contentsApplicationService.findAll();
    }

}
