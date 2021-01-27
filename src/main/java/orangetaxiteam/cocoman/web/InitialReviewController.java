package orangetaxiteam.cocoman.web;

import io.swagger.annotations.ApiOperation;
import orangetaxiteam.cocoman.application.InitialReviewApplicationService;
import orangetaxiteam.cocoman.application.dto.InitialReviewCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.InitialReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/initialReview")
public class InitialReviewController {
    private InitialReviewApplicationService initialReviewApplicationService;

    @Autowired
    public InitialReviewController(InitialReviewApplicationService initialReviewApplicationService){
        this.initialReviewApplicationService = initialReviewApplicationService;
    }

    @PostMapping
    @ApiOperation(value = "Create new review on initial review", tags = "Review")
    public @ResponseBody
    InitialReviewDTO createContents(@RequestBody InitialReviewCreateRequestDTO initialReviewCreateRequestDTO){
        return initialReviewApplicationService.create(initialReviewCreateRequestDTO);
    }
}
