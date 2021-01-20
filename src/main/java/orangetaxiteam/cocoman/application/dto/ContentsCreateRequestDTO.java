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
    @NotBlank
    private String title;
    @NotBlank
    private String year;
    private String country;
    private int runningTime;
    @NotBlank
    private String gradeRate;
    private String broadcaster;
    private String openDate;
    private String broadcastDate;
    @NotBlank
    @Size (max = 500)
    private String story;
    private String posterPath;
    @NotNull
    private List<Long> actorIdList;
}
