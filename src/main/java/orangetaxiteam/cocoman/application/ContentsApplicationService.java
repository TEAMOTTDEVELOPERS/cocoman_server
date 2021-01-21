package orangetaxiteam.cocoman.application;

import orangetaxiteam.cocoman.application.dto.ContentsCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.ContentsDTO;
import orangetaxiteam.cocoman.application.dto.ContentsFindByTitleDTO;
import orangetaxiteam.cocoman.domain.Actor;
import orangetaxiteam.cocoman.domain.ActorService;
import orangetaxiteam.cocoman.domain.ContentsService;
import orangetaxiteam.cocoman.web.exceptions.InputValueValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContentsApplicationService {
    private ContentsService contentsService;
    private ActorService actorService;

    @Autowired
    public ContentsApplicationService(ContentsService contentsService, ActorService actorService) {
        this.contentsService = contentsService;
        this.actorService = actorService;
    }

    public ContentsDTO create(ContentsCreateRequestDTO contentsCreateRequestDTO) {
        List<Actor> actorList = new ArrayList<>();
        for (Long id : contentsCreateRequestDTO.getActorIdList()) {
            Optional<Actor> optionalActor = actorService.findById(id);

            if(optionalActor.isPresent()) actorList.add(optionalActor.get());
            else throw new InputValueValidationException("id - " + id);
        }

        return ContentsDTO.fromDAO(
                contentsService.create(
                        contentsCreateRequestDTO.getTitle(),
                        contentsCreateRequestDTO.getYear(),
                        contentsCreateRequestDTO.getCountry(),
                        contentsCreateRequestDTO.getRunningTime(),
                        contentsCreateRequestDTO.getGradeRate(),
                        contentsCreateRequestDTO.getBroadcaster(),
                        contentsCreateRequestDTO.getOpenDate(),
                        contentsCreateRequestDTO.getBroadcastDate(),
                        contentsCreateRequestDTO.getStory(),
                        contentsCreateRequestDTO.getPosterPath(),
                        actorList
                )
        );
    }

    public List<ContentsDTO> findByTitle(ContentsFindByTitleDTO contentsFindByTitleDTO) {
        return contentsService.findByTitle(contentsFindByTitleDTO.getTitle())
                .stream()
                .map(ContentsDTO::fromDAO)
                .collect(Collectors.toList());
    }

    public List<ContentsDTO> findAll() {
        return contentsService.findAll()
                .stream()
                .map(ContentsDTO::fromDAO)
                .collect(Collectors.toList());
    }
}
