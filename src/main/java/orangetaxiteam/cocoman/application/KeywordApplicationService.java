package orangetaxiteam.cocoman.application;

import orangetaxiteam.cocoman.application.dto.KeywordCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.KeywordDTO;
import orangetaxiteam.cocoman.domain.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KeywordApplicationService {
    private KeywordService keywordService;

    @Autowired
    public KeywordApplicationService(KeywordService keywordService){
        this.keywordService = keywordService;
    }

    @Transactional
    public KeywordDTO create(KeywordCreateRequestDTO keywordCreateRequestDTO) {
        return KeywordDTO.from(
                keywordService.create(
                        keywordCreateRequestDTO.getName()
                )
        );
    }
}
