package orangetaxiteam.cocoman.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import orangetaxiteam.cocoman.domain.Contents;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContentsDTO {
    private Long id;
    private String title;

    public static ContentsDTO FromDao(Contents contents){
        ContentsDTO v = new ContentsDTO();
        v.id = contents.getId();
        v.title = contents.getTitle();
        return v;
    }
}
