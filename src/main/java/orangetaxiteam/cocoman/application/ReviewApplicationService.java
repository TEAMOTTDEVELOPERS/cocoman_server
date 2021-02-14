package orangetaxiteam.cocoman.application;

import orangetaxiteam.cocoman.application.dto.ReviewCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.ReviewDTO;
import orangetaxiteam.cocoman.domain.Contents;
import orangetaxiteam.cocoman.domain.ContentsRepository;
import orangetaxiteam.cocoman.domain.Review;
import orangetaxiteam.cocoman.domain.ReviewRepository;
import orangetaxiteam.cocoman.domain.User;
import orangetaxiteam.cocoman.domain.UserRepository;
import orangetaxiteam.cocoman.domain.exceptions.BadRequestException;
import orangetaxiteam.cocoman.domain.exceptions.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewApplicationService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ContentsRepository contentsRepository;

    public ReviewApplicationService(
            ReviewRepository reviewRepository,
            UserRepository userRepository,
            ContentsRepository contentsRepository
    ) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.contentsRepository = contentsRepository;
    }

    @Transactional(readOnly = true)
    public List<ReviewDTO> findAll() {
        return this.reviewRepository.findAll()
                .stream()
                .map(ReviewDTO::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public ReviewDTO create(ReviewCreateRequestDTO reviewCreateRequestDTO) {
        String userId = reviewCreateRequestDTO.getUserId();
        User user = this.userRepository.findById(userId).orElseThrow(
                () -> new BadRequestException(
                        ErrorCode.ID_DOES_NOT_EXIST,
                        "Invalid user id")
        );

        String contentsId = reviewCreateRequestDTO.getContentsId();
        Contents contents = this.contentsRepository.findById(contentsId).orElseThrow(
                () -> new BadRequestException(
                        ErrorCode.NOT_MATCHED_PARAMETER,
                        String.format("There are no data matches with contents id : %s", contentsId))
        );

        return ReviewDTO.from(
                this.reviewRepository.save(Review.of(
                        reviewCreateRequestDTO.getComment(),
                        user,
                        contents))
        );
    }
}
