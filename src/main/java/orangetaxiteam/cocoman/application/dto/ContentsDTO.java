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
    private String id;
    private String title;
    private String year;
    private String country;
    private Integer runningTime;
    private String gradeRate;
    private String broadcaster;
    private String openDate;
    private String broadcastDate;
    private String story;
    private String posterPath;
    private List<OttDTO> ottList;
    private List<ActorDTO> actorList;
    private List<DirectorDTO> directorList;
    private List<GenreDTO> genreList;
    private List<KeywordDTO> keywordList;

    public static ContentsDTO from(Contents contents) {
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
        v.ottList = contents.getOttSet()
                .stream()
                .map(OttDTO::from)
                .collect(Collectors.toList());
        v.actorList = contents.getActorSet()
                .stream()
                .map(ActorDTO::from)
                .collect(Collectors.toList());
        v.directorList = contents.getDirectorSet()
                .stream()
                .map(DirectorDTO::from)
                .collect(Collectors.toList());
        v.genreList = contents.getGenreSet()
                .stream()
                .map(GenreDTO::from)
                .collect(Collectors.toList());
        v.keywordList = contents.getKeywordSet()
                .stream()
                .map(KeywordDTO::from)
                .collect(Collectors.toList());

        return v;
    }
}
