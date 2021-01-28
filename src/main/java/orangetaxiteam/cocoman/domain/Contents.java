package orangetaxiteam.cocoman.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_CONTENTS")
public class Contents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column (nullable = false)
    private String year;

    @Column
    private String country;

    @Column(name = "running_time")
    private Integer runningTime;

    @Column(name = "grade_rate", nullable = false)
    private String gradeRate;

    @Column
    private String broadcaster;

    @Column(name = "open_date")
    private String openDate;

    @Column(name = "broadcast_date")
    private String broadcastDate;

    @Column(length = 500, nullable = false)
    private String story;

    @Column(name = "poster_path")
    private String posterPath;

    @ManyToMany
    @JoinTable(
            name = "contents_actor",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "contents_id"))
    private Set<Actor> actorSet;

    @ManyToMany
    @JoinTable(
            name = "contents_director",
            joinColumns = @JoinColumn(name = "director_id"),
            inverseJoinColumns = @JoinColumn(name = "contents_id"))
    private Set<Director> directorSet;

    @ManyToMany
    @JoinTable(
            name = "contents_genre",
            joinColumns = @JoinColumn(name = "genre_id"),
            inverseJoinColumns = @JoinColumn(name = "contents_id"))
    private Set<Genre> genreSet;

    @ManyToMany
    @JoinTable(
            name = "contents_keyword",
            joinColumns = @JoinColumn(name = "keyword_id"),
            inverseJoinColumns = @JoinColumn(name = "contents_id"))
    private Set<Keyword> keywordSet;

    @OneToMany (mappedBy = "contents", cascade = CascadeType.ALL)
    private Set<Review> reviewSet;

    // TODO : add FKs - OTT, review

    @Builder
    public Contents(String title, String year, String country, int runningTime, String gradeRate, String broadcaster, String openDate, String broadcastDate, String story, String posterPath, Set<Actor> actorSet, Set<Director> directorSet, Set<Genre> genreSet, Set<Keyword> keywordSet) {
        this.title = title;
        this.year = year;
        this.country = country;
        this.runningTime = runningTime;
        this.gradeRate = gradeRate;
        this.broadcaster = broadcaster;
        this.openDate = openDate;
        this.broadcastDate = broadcastDate;
        this.story = story;
        this.posterPath = posterPath;
        this.actorSet = actorSet;
        this.directorSet = directorSet;
        this.genreSet = genreSet;
        this.keywordSet = keywordSet;
    }
}
