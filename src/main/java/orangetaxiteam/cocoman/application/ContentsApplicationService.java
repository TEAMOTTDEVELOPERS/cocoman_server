package orangetaxiteam.cocoman.application;

import orangetaxiteam.cocoman.application.dto.ContentsDTO;
import orangetaxiteam.cocoman.domain.ContentsRepository;
import orangetaxiteam.cocoman.domain.GenreRepository;
import orangetaxiteam.cocoman.domain.OttRepository;
import orangetaxiteam.cocoman.domain.exceptions.BadRequestException;
import orangetaxiteam.cocoman.domain.exceptions.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContentsApplicationService {
    private final ContentsRepository contentsRepository;
    private final GenreRepository genreRepository;
    private final OttRepository ottRepository;

    public ContentsApplicationService(
            ContentsRepository contentsRepository,
            GenreRepository genreRepository,
            OttRepository ottRepository
    ) {
        this.contentsRepository = contentsRepository;
        this.genreRepository = genreRepository;
        this.ottRepository = ottRepository;
    }

    @Transactional(readOnly = true)
    public ContentsDTO findById(String id) {
        return ContentsDTO.from(
                this.contentsRepository.findById(id).orElseThrow(
                        () -> new BadRequestException(
                                ErrorCode.ROW_DOES_NOT_EXIST,
                                String.format("There are no data matches with contents id : %s", id
                                ))
                )
        );
    }
}
