package orangetaxiteam.cocoman.application;

import orangetaxiteam.cocoman.application.dto.KeywordCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.KeywordDTO;
import orangetaxiteam.cocoman.domain.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeywordApplicationService {
    private KeywordService keywordService;

    @Autowired
    public KeywordApplicationService(KeywordService keywordService){
        this.keywordService = keywordService;
    }

    public KeywordDTO create(KeywordCreateRequestDTO keywordCreateRequestDTO) {
        return KeywordDTO.fromDAO(
                keywordService.create(
                        keywordCreateRequestDTO.getName()
                )
        );
    }
}
