package orangetaxiteam.cocoman.infra;

import orangetaxiteam.cocoman.domain.Contents;
import orangetaxiteam.cocoman.domain.ContentsRecommender;
import orangetaxiteam.cocoman.domain.ContentsRepository;
import orangetaxiteam.cocoman.domain.exceptions.ErrorCode;
import orangetaxiteam.cocoman.domain.exceptions.InternalServerErrorException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultContentsRecommender implements ContentsRecommender {
    private ContentsRepository contentsRepository;

    public DefaultContentsRecommender(ContentsRepository contentsRepository) {
        this.contentsRepository = contentsRepository;
    }

    @Override
    public List<Contents> getRelatedContentsList(List<String> genreList) {
        return this.getRelatedContentsList(
                genreList
                        .stream()
                        .reduce((first, second) -> first + " " + second)
                        .orElseThrow(
                                () -> new InternalServerErrorException(
                                        ErrorCode.INTERNAL_SERVER,
                                        "No genres data in contents"
                                )
                        )
        );
    }

    private List<Contents> getRelatedContentsList(String data) {
        // TODO : get related contents from recommender application
        // Implement by using client
        return new ArrayList<>();
    }
}
