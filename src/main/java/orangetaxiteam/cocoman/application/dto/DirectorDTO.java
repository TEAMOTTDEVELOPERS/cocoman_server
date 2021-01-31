package orangetaxiteam.cocoman.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import orangetaxiteam.cocoman.domain.Director;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DirectorDTO {
    private String id;
    private String name;
    private String imagePath;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static DirectorDTO from(Director director){
        DirectorDTO v = new DirectorDTO();
        v.id = director.getId();
        v.name = director.getName();
        v.imagePath = director.getImagePath();
        v.createdAt = director.getCreatedAt();
        v.updatedAt = director.getUpdatedAt();

        return v;
    }
}
