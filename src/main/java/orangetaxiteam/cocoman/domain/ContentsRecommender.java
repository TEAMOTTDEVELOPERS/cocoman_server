package orangetaxiteam.cocoman.domain;

import java.util.List;

public interface ContentsRecommender {
    List<Contents> getRelatedContentsList(List<String> genreList);
}
