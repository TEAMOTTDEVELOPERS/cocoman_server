package orangetaxiteam.cocoman.domain;

import orangetaxiteam.cocoman.domain.validation.FormatValidation;
import orangetaxiteam.cocoman.web.exceptions.InputValueValidationException;
import orangetaxiteam.cocoman.domain.validation.ValueValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ContentsService {
    private ContentsRepository contentsRepository;

    @Autowired
    public ContentsService(ContentsRepository contentsRepository) {
        this.contentsRepository = contentsRepository;
    }

    public List<Contents> findAll() {
        return new ArrayList<>(contentsRepository.findAll());
    }

    public Contents findById(String id){
        return contentsRepository.findById(id).orElseThrow(
                () -> new InputValueValidationException("invalid contents id")
        );
    }

    public Contents create(String title, String year, String country, int runningTime, String gradeRate, String broadcaster, String openDate, String broadcastDate, String story, String posterPath, List<Actor> actorList, List<Director> directorList, List<Genre> genreList, List<Keyword> keywordList) {

        // Validation for year
        if (!FormatValidation.isNumeric(year)) throw new InputValueValidationException("year format - " + year);
        if (!ValueValidation.isYearInRange(year)) throw new InputValueValidationException("year range - " + year);

        // Validation for open date
        if (openDate != null && !openDate.equals("")) {
            if (!FormatValidation.isDateDot(openDate) && !FormatValidation.isDateHyphen(openDate))
                throw new InputValueValidationException("openDate format - " + openDate + ", allowed 'YYYY.MM.DD' or 'YYYY-MM-DD'");
            if(!ValueValidation.isDateInRange(openDate)) throw new InputValueValidationException("openDate range - " + openDate);
        }

        // Validation for broadcast date
        if (broadcastDate != null && !broadcastDate.equals("")){
            if (!FormatValidation.isDateDot(broadcastDate) && !FormatValidation.isDateHyphen(broadcastDate))
                throw new InputValueValidationException("broadcastDate format - " + broadcastDate + ", allowed 'YYYY.MM.DD' or 'YYYY-MM-DD'");
            if(!ValueValidation.isDateInRange(broadcastDate))
                throw new InputValueValidationException("broadcastDate range - " + broadcastDate);
        }

        return contentsRepository.save(new Contents(title, year, country, runningTime, gradeRate, broadcaster, openDate, broadcastDate, story, posterPath, new HashSet<>(actorList), new HashSet<>(directorList), new HashSet<>(genreList), new HashSet<>(keywordList)));
    }
}
