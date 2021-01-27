package orangetaxiteam.cocoman.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import orangetaxiteam.cocoman.domain.Director;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DirectorDTO {
    private Long id;
    private String name;
    private String imagePath;

    public static DirectorDTO fromDAO(Director director){
        DirectorDTO v = new DirectorDTO();
        v.id = director.getId();
        v.name = director.getName();
        v.imagePath = director.getImagePath();

        return v;
    }
}
