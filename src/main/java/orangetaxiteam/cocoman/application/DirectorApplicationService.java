package orangetaxiteam.cocoman.application;

import orangetaxiteam.cocoman.application.dto.DirectorCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.DirectorDTO;
import orangetaxiteam.cocoman.domain.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DirectorApplicationService {
    private DirectorService directorService;

    @Autowired
    public DirectorApplicationService(DirectorService directorService){
        this.directorService = directorService;
    }

    @Transactional
    public DirectorDTO create(DirectorCreateRequestDTO directorCreateRequestDTO) {
        return DirectorDTO.from(
                directorService.create(
                        directorCreateRequestDTO.getName(),
                        directorCreateRequestDTO.getImagePath()
                )
        );
    }
}
