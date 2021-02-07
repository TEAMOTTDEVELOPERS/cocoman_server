package orangetaxiteam.cocoman.application;

import orangetaxiteam.cocoman.application.dto.ContentsCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.ContentsDTO;
import orangetaxiteam.cocoman.domain.Actor;
import orangetaxiteam.cocoman.domain.ActorService;
import orangetaxiteam.cocoman.domain.ContentsService;
import orangetaxiteam.cocoman.domain.Director;
import orangetaxiteam.cocoman.domain.DirectorService;
import orangetaxiteam.cocoman.domain.Genre;
import orangetaxiteam.cocoman.domain.GenreService;
import orangetaxiteam.cocoman.domain.Keyword;
import orangetaxiteam.cocoman.domain.KeywordService;
import orangetaxiteam.cocoman.domain.exceptions.BadRequestException;
import orangetaxiteam.cocoman.domain.exceptions.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public ContentsDTO create(ContentsCreateRequestDTO contentsCreateRequestDTO) {
        List<Actor> actorList = contentsCreateRequestDTO.getActorIdList().stream()
                .map(this.actorService::findById)
                .map(foundActor -> foundActor.orElseThrow(
                        () -> new BadRequestException(
                                ErrorCode.NOT_MATCHED_PARAMETER,
                                "There are no data matches with actor id"
                        ))
                )
                .collect(Collectors.toList());

        List<Director> directorList = contentsCreateRequestDTO.getDirectorIdList().stream()
                .map(this.directorService::findById)
                .map(foundDirector -> foundDirector.orElseThrow(
                        () -> new BadRequestException(
                                ErrorCode.NOT_MATCHED_PARAMETER,
                                "There are no data matches with director id"
                        ))
                )
                .collect(Collectors.toList());

        List<Genre> genreList = contentsCreateRequestDTO.getGenreIdList().stream()
                .map(this.genreService::findById)
                .map(foundGenre -> foundGenre.orElseThrow(
                        () -> new BadRequestException(
                                ErrorCode.NOT_MATCHED_PARAMETER,
                                "There are no data matches with genre id"
                        ))
                )
                .collect(Collectors.toList());

        List<Keyword> keywordList = Optional.ofNullable(contentsCreateRequestDTO.getKeywordList())
                .orElseGet(Collections::emptyList)
                .stream()
                .map(this.keywordService::findById)
                .map(foundKeyword -> foundKeyword.orElseThrow(
                        () -> new BadRequestException(
                                ErrorCode.NOT_MATCHED_PARAMETER,
                                "There are no data matches with keyword id"
                        ))
                )
                .collect(Collectors.toList());

        return ContentsDTO.from(
                this.contentsService.create(
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

    public ContentsDTO findById(String id) {
        return ContentsDTO.from(
                this.contentsService.findById(id)
        );
    }

    public List<ContentsDTO> findAll() {
        return this.contentsService.findAll()
                .stream()
                .map(ContentsDTO::from)
                .collect(Collectors.toList());
    }
}
