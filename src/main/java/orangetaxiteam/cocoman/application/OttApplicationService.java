package orangetaxiteam.cocoman.application;

import orangetaxiteam.cocoman.application.dto.OttCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.OttDTO;
import orangetaxiteam.cocoman.domain.Ott;
import orangetaxiteam.cocoman.domain.OttRepository;
import orangetaxiteam.cocoman.domain.Pagination;
import orangetaxiteam.cocoman.domain.PaginationFactory;
import orangetaxiteam.cocoman.domain.PaginationFinder;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OttApplicationService extends PaginationFinder {
    private final OttRepository ottRepository;

    public OttApplicationService(
            PaginationFactory paginationFactory,
            OttRepository ottRepository
    ) {
        super(paginationFactory);
        this.ottRepository = ottRepository;
    }

    @Transactional(readOnly = true)
    public Pagination<OttDTO> findAll(Pageable pageable) {
        return this.findAll(() -> this.ottRepository.findAll(pageable)
                .map(OttDTO::from)
        );
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
