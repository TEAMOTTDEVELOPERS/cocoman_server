package orangetaxiteam.cocoman.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KeywordService {
    private KeywordRepository keywordRepository;

    @Autowired
    public KeywordService(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    public Optional<Keyword> findById(String id) {
        return keywordRepository.findById(id);
    }

    public Keyword create(String name) {
        return keywordRepository.save(Keyword.of(name));
    }
}
