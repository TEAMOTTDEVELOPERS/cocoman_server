package orangetaxiteam.cocoman.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import orangetaxiteam.cocoman.domain.Contents;
import orangetaxiteam.cocoman.domain.Review;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContentsDetailDTO {
    private ContentsDTO contentsInfo;
    private Double averageStar;
    private List<ReviewDTO> reviewList;
    private List<ContentsDTO> relatedContentsList;

    public static ContentsDetailDTO from(
            Contents contents,
            Double averageStar,
            List<Review> reviewList,
            List<Contents> relatedContentsList
    ) {
        return new ContentsDetailDTO(
                ContentsDTO.from(contents),
                averageStar,
                reviewList
                        .stream()
                        .map(ReviewDTO::from)
                        .collect(Collectors.toList()),
                relatedContentsList
                        .stream()
                        .map(ContentsDTO::from)
                        .collect(Collectors.toList())
        );
    }
}
