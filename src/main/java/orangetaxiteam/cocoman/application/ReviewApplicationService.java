package orangetaxiteam.cocoman.application;

import orangetaxiteam.cocoman.application.dto.ReviewCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.ReviewDTO;
import orangetaxiteam.cocoman.domain.*;
import orangetaxiteam.cocoman.domain.exceptions.BadRequestException;
import orangetaxiteam.cocoman.domain.exceptions.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewApplicationService {
    private ReviewService reviewService;
    private UserRepository userRepository;
    private ContentsService contentsService;

    @Autowired
    public ReviewApplicationService(ReviewService reviewService, UserRepository userRepository, ContentsService contentsService){
        this.reviewService = reviewService;
        this.userRepository = userRepository;
        this.contentsService = contentsService;
    }

    @Transactional
    public ReviewDTO create(ReviewCreateRequestDTO reviewCreateRequestDTO){
        User user = userRepository.findById(reviewCreateRequestDTO.getUserId())
                .orElseThrow(() -> new BadRequestException(ErrorCode.ID_DOES_NOT_EXIST, "Invalid user id"));
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
