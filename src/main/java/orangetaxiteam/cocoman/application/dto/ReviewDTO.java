package orangetaxiteam.cocoman.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import orangetaxiteam.cocoman.domain.Review;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private String id;
    private Double score;
    private String comment;
    private ContentsDTO contents;
    private UserDTO user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ReviewDTO from(Review review){
        ReviewDTO v = new ReviewDTO();
        v.id = review.getId();
        v.score = review.getScore();
        v.comment = review.getComment();
        v.contents = ContentsDTO.from(review.getContents());
        v.user = UserDTO.from(review.getUser());
        v.createdAt = review.getCreatedAt();
        v.updatedAt = review.getUpdatedAt();

        return v;
    }
}
