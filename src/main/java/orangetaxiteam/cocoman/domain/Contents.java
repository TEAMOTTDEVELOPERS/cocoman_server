package orangetaxiteam.cocoman.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import orangetaxiteam.cocoman.domain.exceptions.BadRequestException;
import orangetaxiteam.cocoman.domain.exceptions.ErrorCode;
import orangetaxiteam.cocoman.domain.validation.FormatValidation;
import orangetaxiteam.cocoman.domain.validation.ValueValidation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TB_CONTENTS")
public class Contents extends DomainEntity {
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
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
    @JoinColumn(name = "ott_id")
    private Set<Ott> ottSet;

    @ManyToMany
    @JoinColumn(name = "actors_id")
    private Set<Actor> actorSet;

    @ManyToMany
    @JoinColumn(name = "directors_id")
    private Set<Director> directorSet;

    @ManyToMany
    @JoinColumn(name = "genres_id")
    private Set<Genre> genreSet;

    @ManyToMany
    @JoinColumn(name = "keywords_id")
    private Set<Keyword> keywordSet;

    public static final int RECENT_REVIEW_COUNT = 5;

    public static final int WEEK_TOP_COUNT = 5;
    public static final int WEEK_TOP_DAYS = 7;

    private Contents(
            String id,
            String title,
            String year,
            String country,
            int runningTime,
            String gradeRate,
            String broadcaster,
            String openDate,
            String broadcastDate,
            String story,
            String posterPath,
            Set<Ott> ottSet,
            Set<Actor> actorSet,
            Set<Director> directorSet,
            Set<Genre> genreSet,
            Set<Keyword> keywordSet
    ) {
        super(id);
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
        this.ottSet = ottSet;
        this.actorSet = actorSet;
        this.directorSet = directorSet;
        this.genreSet = genreSet;
        this.keywordSet = keywordSet;
    }

    public static Contents of(
            String title,
            String year,
            String country,
            int runningTime,
            String gradeRate,
            String broadcaster,
            String openDate,
            String broadcastDate,
            String story,
            String posterPath,
            Set<Ott> ottSet,
            Set<Actor> actorSet,
            Set<Director> directorSet,
            Set<Genre> genreSet,
            Set<Keyword> keywordSet
    ) {
        // Validation for story length
        if (story.length() > 500) {
            throw new BadRequestException(
                    ErrorCode.PARAMETER_FORMAT_ERROR,
                    String.format("Invalid parameter format - story : %s, allowed up to 500 characters", story
                    ));
        }

        // Validation for year
        if (!FormatValidation.isNumeric(year)) {
            throw new BadRequestException(
                    ErrorCode.PARAMETER_FORMAT_ERROR,
                    String.format("Invalid parameter format - year : %s, allowed number only", year
                    ));
        }
        if (!ValueValidation.isYearInRange(year)) {
            throw new BadRequestException(
                    ErrorCode.PARAMETER_FORMAT_ERROR,
                    String.format("Invalid parameter format - year : %s, range outbound", year
                    ));
        }

        // Validation for open date
        if (openDate != null && !openDate.equals("")) {
            if (!FormatValidation.isDateDot(openDate) && !FormatValidation.isDateHyphen(openDate)) {
                throw new BadRequestException(
                        ErrorCode.PARAMETER_FORMAT_ERROR,
                        String.format("Invalid parameter format - openDate : %s, allowed 'YYYY.MM.DD' or 'YYYY-MM-DD'", openDate
                        ));
            }
            if (!ValueValidation.isDateInRange(openDate)) {
                throw new BadRequestException(
                        ErrorCode.PARAMETER_FORMAT_ERROR,
                        String.format("Invalid parameter format - openDate : %s, range outbound", openDate
                        ));
            }
        }

        // Validation for broadcast date
        if (broadcastDate != null && !broadcastDate.equals("")) {
            if (!FormatValidation.isDateDot(broadcastDate) && !FormatValidation.isDateHyphen(broadcastDate)) {
                throw new BadRequestException(
                        ErrorCode.PARAMETER_FORMAT_ERROR,
                        String.format("Invalid parameter format - broadcastDate : %s, allowed 'YYYY.MM.DD' or 'YYYY-MM-DD'", broadcastDate
                        ));
            }
            if (!ValueValidation.isDateInRange(broadcastDate)) {
                throw new BadRequestException(
                        ErrorCode.PARAMETER_FORMAT_ERROR,
                        String.format("Invalid parameter format - broadcastDate : %s, range outbound", broadcastDate
                        ));
            }
        }

        return new Contents(
                null,
                title,
                year,
                country,
                runningTime,
                gradeRate,
                broadcaster,
                openDate,
                broadcastDate,
                story,
                posterPath,
                ottSet,
                actorSet,
                directorSet,
                genreSet,
                keywordSet
        );
    }
}
