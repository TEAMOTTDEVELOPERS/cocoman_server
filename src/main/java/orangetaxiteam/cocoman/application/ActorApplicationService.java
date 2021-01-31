package orangetaxiteam.cocoman.application;

import orangetaxiteam.cocoman.application.dto.ActorCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.ActorDTO;
import orangetaxiteam.cocoman.domain.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActorApplicationService {
    private ActorService actorService;

    @Autowired
    public ActorApplicationService(ActorService actorService){
        this.actorService = actorService;
    }

    public List<ActorDTO> findAll(){
        return actorService.findAll()
                .stream()
                .map(ActorDTO::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public ActorDTO create(ActorCreateRequestDTO actorCreateRequestDTO) {
        return ActorDTO.from(
                actorService.create(
                        actorCreateRequestDTO.getName(),
                        actorCreateRequestDTO.getImagePath()
                )
        );
    }
}
