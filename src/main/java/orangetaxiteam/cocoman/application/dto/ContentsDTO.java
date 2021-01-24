package orangetaxiteam.cocoman.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import orangetaxiteam.cocoman.domain.Contents;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContentsDTO {
    private Long id;
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
    private List<ActorDTO> actorList;

    public static ContentsDTO fromDAO(Contents contents){
        ContentsDTO v = new ContentsDTO();
        v.id = contents.getId();
        v.title = contents.getTitle();
        v.year = contents.getYear();
        v.country = contents.getCountry();
        v.runningTime = contents.getRunningTime();
        v.gradeRate = contents.getGradeRate();
        v.broadcaster = contents.getBroadcaster();
        v.openDate = contents.getOpenDate();
        v.broadcastDate = contents.getBroadcastDate();
        v.story = contents.getStory();
        v.posterPath = contents.getPosterPath();
        v.actorList = contents.getActorSet()
                .stream()
                .map(ActorDTO::fromDAO)
                .collect(Collectors.toList());

        return v;
    }
}
