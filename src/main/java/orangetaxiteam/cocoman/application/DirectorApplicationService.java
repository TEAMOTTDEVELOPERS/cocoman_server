package orangetaxiteam.cocoman.application;

import orangetaxiteam.cocoman.application.dto.DirectorCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.DirectorDTO;
import orangetaxiteam.cocoman.domain.Director;
import orangetaxiteam.cocoman.domain.DirectorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DirectorApplicationService {
    private DirectorRepository directorRepository;

    public DirectorApplicationService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Transactional(readOnly = true)
    public List<DirectorDTO> findAll() {
        return this.directorRepository.findAll()
                .stream()
                .map(DirectorDTO::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public DirectorDTO create(DirectorCreateRequestDTO directorCreateRequestDTO) {
        return DirectorDTO.from(
                this.directorRepository.save(Director.of(
                        directorCreateRequestDTO.getName(),
                        directorCreateRequestDTO.getImagePath()))
        );
    }
}
