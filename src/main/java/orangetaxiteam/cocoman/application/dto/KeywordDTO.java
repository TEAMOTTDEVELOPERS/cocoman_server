package orangetaxiteam.cocoman.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import orangetaxiteam.cocoman.domain.Keyword;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KeywordDTO {
    private String id;
    private String name;

    public static KeywordDTO from(Keyword keyword) {
        KeywordDTO v = new KeywordDTO();
        v.id = keyword.getId();
        v.name = keyword.getName();

        return v;
    }
}
