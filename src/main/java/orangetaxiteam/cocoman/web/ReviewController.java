package orangetaxiteam.cocoman.web;

import orangetaxiteam.cocoman.application.ReviewApplicationService;
import orangetaxiteam.cocoman.application.dto.ReviewCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.ReviewDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewApplicationService reviewApplicationService;

    public ReviewController(ReviewApplicationService reviewApplicationService) {
        this.reviewApplicationService = reviewApplicationService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ReviewDTO createReview(@RequestBody ReviewCreateRequestDTO reviewCreateRequestDTO) {
        return this.reviewApplicationService.create(reviewCreateRequestDTO);
    }
}
