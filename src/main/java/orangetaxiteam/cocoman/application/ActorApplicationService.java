package orangetaxiteam.cocoman.application;

import orangetaxiteam.cocoman.application.dto.ActorCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.ActorDTO;
import orangetaxiteam.cocoman.domain.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                .map(ActorDTO::fromDAO)
                .collect(Collectors.toList());
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
