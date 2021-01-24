package orangetaxiteam.cocoman.domain;

import orangetaxiteam.cocoman.web.exceptions.FormatValidation;
import orangetaxiteam.cocoman.web.exceptions.InputValueValidationException;
import orangetaxiteam.cocoman.web.exceptions.ValueValidation;
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
        return new ArrayList<>(contentsRepository.findAll());
    }

    public List<Contents> findByTitle(String title) {
        return new ArrayList<>(contentsRepository.findByTitle(title));
    }

    public Contents create(String title, String year, String country, int runningTime, String gradeRate, String broadcaster,
                           String openDate, String broadcastDate, String story, String posterPath, List<Actor> actorList) {

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
            if(!ValueValidation.isDateInRange(broadcastDate)) throw new InputValueValidationException("broadcastDate range - " + broadcastDate);
        }

        return contentsRepository.save(new Contents(title, year, country, runningTime, gradeRate, broadcaster, openDate,
                broadcastDate, story, posterPath, new HashSet<>(actorList)));
    }
}
