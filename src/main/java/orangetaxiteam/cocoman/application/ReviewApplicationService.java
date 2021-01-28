package orangetaxiteam.cocoman.application;

import orangetaxiteam.cocoman.application.dto.ReviewCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.ReviewDTO;
import orangetaxiteam.cocoman.domain.*;
import orangetaxiteam.cocoman.web.exceptions.InputValueValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewApplicationService {
    private ReviewService reviewService;
    private UserService userService;
    private ContentsService contentsService;

    @Autowired
    public ReviewApplicationService(ReviewService reviewService, UserService userService, ContentsService contentsService){
        this.reviewService = reviewService;
        this.userService = userService;
        this.contentsService = contentsService;
    }

    public ReviewDTO create(ReviewCreateRequestDTO reviewCreateRequestDTO){
        User user = userService.findById(reviewCreateRequestDTO.getUserId()).orElseThrow(
                () -> new InputValueValidationException("invalid user id")
        );

        Contents contents = contentsService.findById(reviewCreateRequestDTO.getContentsId()).orElseThrow(
                () -> new InputValueValidationException("invalid contents id")
        );


        return ReviewDTO.fromDAO(
                reviewService.create(
                        reviewCreateRequestDTO.getScore(),
                        reviewCreateRequestDTO.getComment(),
                        user,
                        contents
                )
        );
    }
}
