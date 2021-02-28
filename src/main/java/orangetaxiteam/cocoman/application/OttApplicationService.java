package orangetaxiteam.cocoman.application;

import orangetaxiteam.cocoman.application.dto.OttCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.OttDTO;
import orangetaxiteam.cocoman.domain.Ott;
import orangetaxiteam.cocoman.domain.OttRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OttApplicationService {
    private final OttRepository ottRepository;

    public OttApplicationService(OttRepository ottRepository) {
        this.ottRepository = ottRepository;
    }

    @Transactional(readOnly = true)
    public List<OttDTO> findAll() {
        return this.ottRepository.findAll()
                .stream()
                .map(OttDTO::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public OttDTO create(OttCreateRequestDTO ottCreateRequestDTO) {
        return OttDTO.from(
                this.ottRepository.save(Ott.of(
                        ottCreateRequestDTO.getName(),
                        ottCreateRequestDTO.getImagePath()
                ))
        );
    }
}
