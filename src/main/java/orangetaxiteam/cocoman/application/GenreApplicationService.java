package orangetaxiteam.cocoman.application;

import orangetaxiteam.cocoman.application.dto.GenreCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.GenreDTO;
import orangetaxiteam.cocoman.domain.Genre;
import orangetaxiteam.cocoman.domain.GenreRepository;
import orangetaxiteam.cocoman.domain.Pagination;
import orangetaxiteam.cocoman.domain.PaginationFactory;
import orangetaxiteam.cocoman.domain.PaginationFinder;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GenreApplicationService extends PaginationFinder {
    private final GenreRepository genreRepository;

    public GenreApplicationService(
            PaginationFactory paginationFactory,
            GenreRepository genreRepository
    ) {
        super(paginationFactory);
        this.genreRepository = genreRepository;
    }

    @Transactional(readOnly = true)
    public Pagination<GenreDTO> findAll(Pageable pageable) {
        return this.findAll(
                () -> this.genreRepository.findAll(pageable)
                        .map(GenreDTO::from)
        );
    }

    @Transactional
    public GenreDTO create(GenreCreateRequestDTO genreCreateRequestDTO) {
        return GenreDTO.from(
                this.genreRepository.save(Genre.of(
                        genreCreateRequestDTO.getName()))
        );
    }
}
