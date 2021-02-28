package orangetaxiteam.cocoman.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContentsCreateRequestDTO {
    private String title;
    private String year;
    private String country;
    private int runningTime;
    private String gradeRate;
    private String broadcaster;
    private String openDate;
    private String broadcastDate;
    private String story;
    private String posterPath;
    private List<String> ottIdList;
    private List<String> actorIdList;
    private List<String> directorIdList;
    private List<String> genreIdList;
    private List<String> keywordList;
}
