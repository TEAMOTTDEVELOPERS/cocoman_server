package orangetaxiteam.cocoman.application;

import orangetaxiteam.cocoman.application.dto.KeywordCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.KeywordDTO;
import orangetaxiteam.cocoman.domain.Keyword;
import orangetaxiteam.cocoman.domain.KeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeywordApplicationService {
    private KeywordRepository keywordRepository;

    @Autowired
    public KeywordApplicationService(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    @Transactional(readOnly = true)
    public List<KeywordDTO> findAll() {
        return this.keywordRepository.findAll()
                .stream()
                .map(KeywordDTO::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public KeywordDTO create(KeywordCreateRequestDTO keywordCreateRequestDTO) {
        return KeywordDTO.from(
                this.keywordRepository.save(Keyword.of(
                        keywordCreateRequestDTO.getName()))
        );
    }
}
