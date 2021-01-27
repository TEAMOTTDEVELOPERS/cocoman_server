package orangetaxiteam.cocoman.application;

import orangetaxiteam.cocoman.application.dto.ContentsCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.ContentsDTO;
import orangetaxiteam.cocoman.domain.*;
import orangetaxiteam.cocoman.web.exceptions.InputValueValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContentsApplicationService {
    private ContentsService contentsService;
    private ActorService actorService;
    private DirectorService directorService;
    private GenreService genreService;
    private KeywordService keywordService;

    @Autowired
    public ContentsApplicationService(ContentsService contentsService, ActorService actorService, DirectorService directorService, GenreService genreService, KeywordService keywordService) {
        this.contentsService = contentsService;
        this.actorService = actorService;
        this.directorService = directorService;
        this.genreService = genreService;
        this.keywordService = keywordService;
    }

    public ContentsDTO create(ContentsCreateRequestDTO contentsCreateRequestDTO) {
        List<Actor> actorList = contentsCreateRequestDTO.getActorIdList().stream()
                .map(actorService::findById)
                .map(foundActor -> foundActor.orElseThrow(
                        () -> new InputValueValidationException("invalid actor id"))
                )
                .collect(Collectors.toList());

        List<Director> directorList = contentsCreateRequestDTO.getDirectorIdList().stream()
                .map(directorService::findById)
                .map(foundDirector -> foundDirector.orElseThrow(
                        () -> new InputValueValidationException("invalid director id"))
                )
                .collect(Collectors.toList());

        List<Genre> genreList = contentsCreateRequestDTO.getGenreIdList().stream()
                .map(genreService::findById)
                .map(foundGenre -> foundGenre.orElseThrow(
                        () -> new InputValueValidationException("invalid genre id"))
                )
                .collect(Collectors.toList());

        List<Keyword> keywordList = Optional.ofNullable(contentsCreateRequestDTO.getKeywordList())
                .orElseGet(Collections::emptyList)
                .stream()
                .map(keywordService::findById)
                .map(foundKeyword -> foundKeyword.orElseThrow(
                        () -> new InputValueValidationException("invalid keyword id"))
                )
                .collect(Collectors.toList());

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
                        actorList,
                        directorList,
                        genreList,
                        keywordList
                )
        );
    }

    public ContentsDTO findById(Long id) {
        return ContentsDTO.fromDAO(
                contentsService.findById(id).orElseThrow(() -> new InputValueValidationException("invalid contents id"))
        );
    }

    public List<ContentsDTO> findAll() {
        return contentsService.findAll()
                .stream()
                .map(ContentsDTO::fromDAO)
                .collect(Collectors.toList());
    }
}
