package orangetaxiteam.cocoman.domain;

import orangetaxiteam.cocoman.domain.validation.ValueValidation;
import orangetaxiteam.cocoman.domain.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review create(Double score, String comment, User user, Contents contents) {
        if (!ValueValidation.isScoreInRange(score)) throw new BadRequestException("score range - " + score);

        return reviewRepository.save(Review.of(score, comment, user, contents));
    }
}
