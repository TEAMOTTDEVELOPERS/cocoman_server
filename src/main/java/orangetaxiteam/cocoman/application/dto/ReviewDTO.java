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
    private String comment;
    private String contentsId;
    private String userId;
    private String userNickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ReviewDTO from(Review review) {
        ReviewDTO v = new ReviewDTO();
        v.id = review.getId();
        v.comment = review.getComment();
        v.contentsId = review.getContents().getId();
        v.userId = review.getUser().getId();
        v.userNickname = review.getUser().getNickName();
        v.createdAt = review.getCreatedAt();
        v.updatedAt = review.getUpdatedAt();

        return v;
    }
}
