package orangetaxiteam.cocoman.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import orangetaxiteam.cocoman.domain.Ott;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OttDTO {
    private String id;
    private String name;
    private String imagePath;

    public static OttDTO from(Ott ott) {
        OttDTO ottDTO = new OttDTO();

        ottDTO.id = ott.getId();
        ottDTO.name = ott.getName();
        ottDTO.imagePath = ott.getImagePath();

        return ottDTO;
    }
}
