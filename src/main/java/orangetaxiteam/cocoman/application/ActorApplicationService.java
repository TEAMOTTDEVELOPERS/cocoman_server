package orangetaxiteam.cocoman.application;

import orangetaxiteam.cocoman.application.dto.ActorCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.ActorDTO;
import orangetaxiteam.cocoman.domain.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorApplicationService {
    private ActorService actorService;

    @Autowired
    public ActorApplicationService(ActorService actorService){
        this.actorService = actorService;
    }

    public ActorDTO create(ActorCreateRequestDTO actorCreateRequestDTO) {
        return ActorDTO.fromDAO(
                actorService.create(
                        actorCreateRequestDTO.getName(),
                        actorCreateRequestDTO.getImagePath()
                )
        );
    }
}
