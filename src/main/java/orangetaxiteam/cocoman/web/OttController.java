package orangetaxiteam.cocoman.web;

import orangetaxiteam.cocoman.application.OttApplicationService;
import orangetaxiteam.cocoman.application.dto.OttDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ott")
public class OttController {
    private final OttApplicationService ottApplicationService;

    public OttController(OttApplicationService ottApplicationService) {
        this.ottApplicationService = ottApplicationService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<OttDTO> findAll() {
        return this.ottApplicationService.findAll();
    }
}
