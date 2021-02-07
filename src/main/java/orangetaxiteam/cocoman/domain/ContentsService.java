package orangetaxiteam.cocoman.domain;

import orangetaxiteam.cocoman.domain.exceptions.BadRequestException;
import orangetaxiteam.cocoman.domain.exceptions.ErrorCode;
import orangetaxiteam.cocoman.domain.validation.FormatValidation;
import orangetaxiteam.cocoman.domain.validation.ValueValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class ContentsService {
    private ContentsRepository contentsRepository;

    @Autowired
    public ContentsService(ContentsRepository contentsRepository) {
        this.contentsRepository = contentsRepository;
    }

    public List<Contents> findAll() {
        return new ArrayList<>(this.contentsRepository.findAll());
    }

    public Contents findById(String id) {
        return this.contentsRepository.findById(id).orElseThrow(
                () -> new BadRequestException(
                        ErrorCode.NOT_MATCHED_PARAMETER,
                        String.format("There are no data matches with contents id : %s", id
                        ))
        );
    }

    public Contents create(
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
            List<Actor> actorList,
            List<Director> directorList,
            List<Genre> genreList,
            List<Keyword> keywordList
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

        return this.contentsRepository.save(Contents.of(title, year, country, runningTime, gradeRate, broadcaster, openDate, broadcastDate, story, posterPath, new HashSet<>(actorList), new HashSet<>(directorList), new HashSet<>(genreList), new HashSet<>(keywordList)));
    }
}
