package orangetaxiteam.cocoman.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import orangetaxiteam.cocoman.domain.Contents;
import orangetaxiteam.cocoman.domain.StarRating;
import orangetaxiteam.cocoman.domain.User;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StarRatingDTO {
    private double rating;
    private User user;
    private Contents contents;

    public static StarRatingDTO from(StarRating starRating){
        StarRatingDTO starRatingDTO = new StarRatingDTO();
        starRatingDTO.rating = starRating.getRating();
        starRatingDTO.user = starRating.getUser();
        starRatingDTO.contents = starRating.getContents();

        return starRatingDTO;
    }
}
