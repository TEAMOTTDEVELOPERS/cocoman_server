package orangetaxiteam.cocoman.domain;

import orangetaxiteam.cocoman.domain.validation.ValueValidation;
import orangetaxiteam.cocoman.web.exceptions.InputValueValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitialReviewService {
    private InitialReviewRepository initialReviewRepository;

    @Autowired
    public InitialReviewService(InitialReviewRepository initialReviewRepository) {
        this.initialReviewRepository = initialReviewRepository;
    }

    public InitialReview create(Double score, User user, Contents contents) {
        if(!ValueValidation.isScoreInRange(score)) throw new InputValueValidationException("score range - " + score);

        return initialReviewRepository.save(new InitialReview(score, user, contents));
    }
}
