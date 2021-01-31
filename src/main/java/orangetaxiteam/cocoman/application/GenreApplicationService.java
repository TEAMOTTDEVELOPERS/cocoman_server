package orangetaxiteam.cocoman.application;

import orangetaxiteam.cocoman.application.dto.GenreCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.GenreDTO;
import orangetaxiteam.cocoman.domain.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GenreApplicationService {
    private GenreService genreService;

    @Autowired
    public GenreApplicationService(GenreService genreService){
        this.genreService = genreService;
    }

    @Transactional
    public GenreDTO create(GenreCreateRequestDTO genreCreateRequestDTO) {
        return GenreDTO.from(
                genreService.create(
                        genreCreateRequestDTO.getName()
                )
        );
    }
}
