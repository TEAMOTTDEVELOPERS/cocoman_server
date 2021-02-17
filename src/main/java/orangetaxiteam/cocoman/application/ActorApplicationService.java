package orangetaxiteam.cocoman.application;

import orangetaxiteam.cocoman.application.dto.ActorCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.ActorDTO;
import orangetaxiteam.cocoman.domain.Actor;
import orangetaxiteam.cocoman.domain.ActorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActorApplicationService {
    private ActorRepository actorRepository;

    public ActorApplicationService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
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
}
