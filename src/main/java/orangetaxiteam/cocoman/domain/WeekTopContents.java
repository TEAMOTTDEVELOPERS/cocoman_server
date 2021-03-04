package orangetaxiteam.cocoman.domain;

import lombok.Getter;

@Getter
public class WeekTopContents {
    private Contents contents;
    private Double averageRating;

    public WeekTopContents(Contents contents, Double averageRating) {
        this.contents = contents;
        this.averageRating = averageRating;
    }
}
