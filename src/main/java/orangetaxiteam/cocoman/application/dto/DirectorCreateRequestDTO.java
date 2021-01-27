package orangetaxiteam.cocoman.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DirectorCreateRequestDTO {
    @NotBlank(message = "Director name cannot be null or blank")
    private String name;
    private String imagePath;
}
