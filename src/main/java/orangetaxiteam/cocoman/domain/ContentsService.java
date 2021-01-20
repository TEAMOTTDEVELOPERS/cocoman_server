package orangetaxiteam.cocoman.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class ContentsService {
    private ContentsRepository contentsRepository;

    @Autowired
    public ContentsService (ContentsRepository contentsRepository){
        this.contentsRepository = contentsRepository;
    }

    public List<Contents> findAll() {
        return new ArrayList<>(contentsRepository.findAll());
    }

    public List<Contents> findByTitle(String title){
        return new ArrayList<>(contentsRepository.findByTitle(title));
    }

    public Contents create(String title, String year, String country, int runningTime, String gradeRate, String broadcaster,
                           String openDate, String broadcastDate, String story, String posterPath, List<Actor> actorList) {
        return contentsRepository.save(new Contents(title, year, country, runningTime, gradeRate, broadcaster, openDate,
                broadcastDate, story, posterPath, new HashSet<>(actorList)));
    }
}
