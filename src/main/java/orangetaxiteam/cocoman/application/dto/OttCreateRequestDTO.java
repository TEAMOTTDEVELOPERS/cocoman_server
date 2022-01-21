package orangetaxiteam.cocoman.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import orangetaxiteam.cocoman.domain.Contents;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OttCreateRequestDTO {
    private String name;
    private String imagePath;
    private String ratePlan;
    private Set<Contents> contentsSet;
}
