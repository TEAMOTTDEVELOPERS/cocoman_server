package orangetaxiteam.cocoman.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import orangetaxiteam.cocoman.domain.Ott;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OttDTO {
    private String id;
    private String name;
    private String imagePath;
    private String ratePlan;
    private List<ContentsDTO> contentsList;

    public static OttDTO from(Ott ott) {
        OttDTO ottDTO = new OttDTO();

        ottDTO.id = ott.getId();
        ottDTO.name = ott.getName();
        ottDTO.imagePath = ott.getImagePath();
        ottDTO.ratePlan = ott.getRatePlan();
        ottDTO.contentsList = ott.getContentsSet()
                .stream()
                .map(ContentsDTO::from)
                .collect(Collectors.toList());

        return ottDTO;
    }
}
