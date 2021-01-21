package orangetaxiteam.cocoman.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContentsCreateRequestDTO {
    @NotBlank(message = "Contents Title cannot be null or blank")
    private String title;
    @NotBlank(message = "Contents Year cannot be null or blank")
    private String year;
    private String country;
    private int runningTime;
    @NotBlank(message = "Contents Grade rate cannot be null or blank")
    private String gradeRate;
    private String broadcaster;
    private String openDate;
    private String broadcastDate;
    @NotBlank(message = "Contents Story cannot be null or blank")
    @Size(max = 500, message = "Contents Story cannot exceed 500 characters")
    private String story;
    private String posterPath;
    @NotNull(message = "Contents actor list cannot be null")
    private List<Long> actorIdList;
}
