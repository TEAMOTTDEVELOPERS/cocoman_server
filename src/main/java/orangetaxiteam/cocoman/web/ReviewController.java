package orangetaxiteam.cocoman.web;

import io.swagger.annotations.ApiOperation;
import orangetaxiteam.cocoman.application.ReviewApplicationService;
import orangetaxiteam.cocoman.application.dto.ReviewCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {
    private ReviewApplicationService reviewApplicationService;

    @Autowired
    public ReviewController(ReviewApplicationService reviewApplicationService){
        this.reviewApplicationService = reviewApplicationService;
    }

    @PostMapping
    public @ResponseBody
    ReviewDTO createReview(@RequestBody ReviewCreateRequestDTO reviewCreateRequestDTO){
        return reviewApplicationService.create(reviewCreateRequestDTO);
    }
}
