package orangetaxiteam.cocoman.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreService {
    private GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Optional<Genre> findById(String id) {
        return genreRepository.findById(id);
    }

    public Genre create(String name) {
        return genreRepository.save(Genre.of(name));
    }
}
