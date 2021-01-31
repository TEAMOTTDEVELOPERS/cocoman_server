package orangetaxiteam.cocoman.application;

import orangetaxiteam.cocoman.application.dto.ReviewCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.ReviewDTO;
import orangetaxiteam.cocoman.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public ReviewDTO create(ReviewCreateRequestDTO reviewCreateRequestDTO){
        User user = userService.findById(reviewCreateRequestDTO.getUserId());
        Contents contents = contentsService.findById(reviewCreateRequestDTO.getContentsId());


        return ReviewDTO.from(
                reviewService.create(
                        reviewCreateRequestDTO.getScore(),
                        reviewCreateRequestDTO.getComment(),
                        user,
                        contents
                )
        );
    }
}
