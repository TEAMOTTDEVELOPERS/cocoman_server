package orangetaxiteam.cocoman.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import orangetaxiteam.cocoman.domain.exceptions.BadRequestException;
import orangetaxiteam.cocoman.domain.exceptions.ErrorCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TB_STAR")
public class StarRating extends DomainEntity {
    @Column(name = "rating")
    private double rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "contents_id")
    private Contents contents;

    private StarRating(
            double rating,
            User user,
            Contents contents
    ) {
        this.checkRatingRange(rating);

        this.rating = ((int) rating * 2) / 2.0;
        this.user = user;
        this.contents = contents;
    }

    public static StarRating of(double rating, User user, Contents contents) {
        return new StarRating(
                rating,
                user,
                contents
        );
    }

    public void update(double rating) {
        this.checkRatingRange(rating);

        this.rating = ((int) rating * 2) / 2.0;
    }

    private void checkRatingRange(double rating) {
        int checkRatingRange = (int) rating * 2;

        if (checkRatingRange < 1 || checkRatingRange > 10) {
            throw new BadRequestException(ErrorCode.PARAMETER_FORMAT_ERROR,
                    String.format("Invalid parameter format - rating : %f", rating));
        }

    }

}
