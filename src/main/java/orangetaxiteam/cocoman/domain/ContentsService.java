package orangetaxiteam.cocoman.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentsService {
    @Autowired
    private ContentsRepository contentsRepository;

    public List<Contents> findAll() {
        return new ArrayList<Contents>(contentsRepository.findAll());
    }

    public Contents create(String title) {
        return contentsRepository.save(new Contents(title));
    }
}
