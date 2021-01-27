package orangetaxiteam.cocoman.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import orangetaxiteam.cocoman.domain.InitialReview;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InitialReviewDTO {
    private Long id;
    private Double score;
    private ContentsDTO contents;
    private UserDTO user;

    public static InitialReviewDTO fromDAO(InitialReview initialReview){
        InitialReviewDTO v = new InitialReviewDTO();
        v.id = initialReview.getId();
        v.score = initialReview.getScore();
        v.contents = ContentsDTO.fromDAO(initialReview.getContents());
        v.user = UserDTO.fromDAO(initialReview.getUser());

        return v;
    }
}
