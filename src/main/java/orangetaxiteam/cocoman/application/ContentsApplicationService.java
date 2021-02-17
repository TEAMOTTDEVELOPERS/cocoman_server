package orangetaxiteam.cocoman.application;

import orangetaxiteam.cocoman.application.dto.ContentsCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.ContentsDTO;
import orangetaxiteam.cocoman.domain.Actor;
import orangetaxiteam.cocoman.domain.ActorRepository;
import orangetaxiteam.cocoman.domain.Contents;
import orangetaxiteam.cocoman.domain.ContentsRepository;
import orangetaxiteam.cocoman.domain.Director;
import orangetaxiteam.cocoman.domain.DirectorRepository;
import orangetaxiteam.cocoman.domain.Genre;
import orangetaxiteam.cocoman.domain.GenreRepository;
import orangetaxiteam.cocoman.domain.Keyword;
import orangetaxiteam.cocoman.domain.KeywordRepository;
import orangetaxiteam.cocoman.domain.Ott;
import orangetaxiteam.cocoman.domain.OttRepository;
import orangetaxiteam.cocoman.domain.exceptions.BadRequestException;
import orangetaxiteam.cocoman.domain.exceptions.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContentsApplicationService {
    private final ContentsRepository contentsRepository;
    private final ActorRepository actorRepository;
    private final DirectorRepository directorRepository;
    private final GenreRepository genreRepository;
    private final KeywordRepository keywordRepository;
    private final OttRepository ottRepository;

    public ContentsApplicationService(
            ContentsRepository contentsRepository,
            ActorRepository actorRepository,
            DirectorRepository directorRepository,
            GenreRepository genreRepository,
            KeywordRepository keywordRepository,
            OttRepository ottRepository
    ) {
        this.contentsRepository = contentsRepository;
        this.actorRepository = actorRepository;
        this.directorRepository = directorRepository;
        this.genreRepository = genreRepository;
        this.keywordRepository = keywordRepository;
        this.ottRepository = ottRepository;
    }

    @Transactional
    public ContentsDTO create(ContentsCreateRequestDTO contentsCreateRequestDTO) {
        List<Ott> ottList = contentsCreateRequestDTO.getOttIdList().stream()
                .map(this.ottRepository::findById)
                .map(foundOtt -> foundOtt.orElseThrow(
                        () -> new BadRequestException(
                                ErrorCode.NOT_MATCHED_PARAMETER,
                                "There are no data matches with OTT id"
                        ))
                )
                .collect(Collectors.toList());

        List<Actor> actorList = contentsCreateRequestDTO.getActorIdList().stream()
                .map(this.actorRepository::findById)
                .map(foundActor -> foundActor.orElseThrow(
                        () -> new BadRequestException(
                                ErrorCode.NOT_MATCHED_PARAMETER,
                                "There are no data matches with actor id"
                        ))
                )
                .collect(Collectors.toList());

        List<Director> directorList = contentsCreateRequestDTO.getDirectorIdList().stream()
                .map(this.directorRepository::findById)
                .map(foundDirector -> foundDirector.orElseThrow(
                        () -> new BadRequestException(
                                ErrorCode.NOT_MATCHED_PARAMETER,
                                "There are no data matches with director id"
                        ))
                )
                .collect(Collectors.toList());

        List<Genre> genreList = contentsCreateRequestDTO.getGenreIdList().stream()
                .map(this.genreRepository::findById)
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
                .map(this.keywordRepository::findById)
                .map(foundKeyword -> foundKeyword.orElseThrow(
                        () -> new BadRequestException(
                                ErrorCode.NOT_MATCHED_PARAMETER,
                                "There are no data matches with keyword id"
                        ))
                )
                .collect(Collectors.toList());

        return ContentsDTO.from(
                this.contentsRepository.save(Contents.of(
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
                        new HashSet<>(ottList),
                        new HashSet<>(actorList),
                        new HashSet<>(directorList),
                        new HashSet<>(genreList),
                        new HashSet<>(keywordList)
                ))
        );
    }

    @Transactional(readOnly = true)
    public ContentsDTO findById(String id) {
        return ContentsDTO.from(
                this.contentsRepository.findById(id).orElseThrow(
                        () -> new BadRequestException(
                                ErrorCode.NOT_MATCHED_PARAMETER,
                                String.format("There are no data matches with contents id : %s", id
                                ))
                )
        );
    }

    @Transactional(readOnly = true)
    public List<ContentsDTO> findAll() {
        return this.contentsRepository.findAll()
                .stream()
                .map(ContentsDTO::from)
                .collect(Collectors.toList());
    }
}
