package orangetaxiteam.cocoman.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import orangetaxiteam.cocoman.domain.Keyword;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KeywordDTO {
    private String id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static KeywordDTO from(Keyword keyword){
        KeywordDTO v = new KeywordDTO();
        v.id = keyword.getId();
        v.name = keyword.getName();
        v.createdAt = keyword.getCreatedAt();
        v.updatedAt = keyword.getUpdatedAt();

        return v;
    }
}
