package orangetaxiteam.cocoman.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import orangetaxiteam.cocoman.domain.Review;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Long id;
    private Double score;
    private String comment;
    private ContentsDTO contents;
    private UserDTO user;

    public static ReviewDTO fromDAO(Review review){
        ReviewDTO v = new ReviewDTO();
        v.id = review.getId();
        v.score = review.getScore();
        v.comment = review.getComment();
        v.contents = ContentsDTO.fromDAO(review.getContents());
        v.user = UserDTO.fromDAO(review.getUser());

        return v;
    }
}
