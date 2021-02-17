package orangetaxiteam.cocoman.application;

import orangetaxiteam.cocoman.application.dto.GenreCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.GenreDTO;
import orangetaxiteam.cocoman.domain.Genre;
import orangetaxiteam.cocoman.domain.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreApplicationService {
    private GenreRepository genreRepository;

    public GenreApplicationService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Transactional(readOnly = true)
    public List<GenreDTO> findAll() {
        return this.genreRepository.findAll()
                .stream()
                .map(GenreDTO::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public GenreDTO create(GenreCreateRequestDTO genreCreateRequestDTO) {
        return GenreDTO.from(
                this.genreRepository.save(Genre.of(
                        genreCreateRequestDTO.getName()))
        );
    }
}
