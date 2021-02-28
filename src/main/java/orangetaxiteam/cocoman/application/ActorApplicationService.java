package orangetaxiteam.cocoman.application;

import orangetaxiteam.cocoman.application.dto.ActorCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.ActorDTO;
import orangetaxiteam.cocoman.domain.Actor;
import orangetaxiteam.cocoman.domain.ActorRepository;
import orangetaxiteam.cocoman.domain.FileUploader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActorApplicationService {
    private final ActorRepository actorRepository;

    private final FileUploader fileUploader;

    public ActorApplicationService(ActorRepository actorRepository, FileUploader fileUploader) {
        this.actorRepository = actorRepository;
        this.fileUploader = fileUploader;
    }

    @Transactional(readOnly = true)
    public List<ActorDTO> findAll() {
        return this.actorRepository.findAll()
                .stream()
                .map(ActorDTO::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public ActorDTO create(ActorCreateRequestDTO actorCreateRequestDTO) {
        return ActorDTO.from(
                this.actorRepository.save(Actor.of(
                        actorCreateRequestDTO.getName(),
                        actorCreateRequestDTO.getImagePath()))
        );
    }

    // TODO : 삭제하기 (Skel)
    public String uploadImage(MultipartFile file) {
        return this.fileUploader.upload(file, "static/image/actor");
    }
}
