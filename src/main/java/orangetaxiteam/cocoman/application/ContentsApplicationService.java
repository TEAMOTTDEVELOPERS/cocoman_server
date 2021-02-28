package orangetaxiteam.cocoman.application;

import orangetaxiteam.cocoman.application.dto.ContentsDTO;
import orangetaxiteam.cocoman.domain.Contents;
import orangetaxiteam.cocoman.domain.ContentsRepository;
import orangetaxiteam.cocoman.domain.GenreRepository;
import orangetaxiteam.cocoman.domain.OttRepository;
import orangetaxiteam.cocoman.domain.SearchHistory;
import orangetaxiteam.cocoman.domain.SearchHistoryRepository;
import orangetaxiteam.cocoman.domain.User;
import orangetaxiteam.cocoman.domain.UserRepository;
import orangetaxiteam.cocoman.domain.exceptions.BadRequestException;
import orangetaxiteam.cocoman.domain.exceptions.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContentsApplicationService {
    private final ContentsRepository contentsRepository;
    private final GenreRepository genreRepository;
    private final OttRepository ottRepository;
    private final SearchHistoryRepository searchHistoryRepository;
    private final UserRepository userRepository;

    public ContentsApplicationService(
            ContentsRepository contentsRepository,
            GenreRepository genreRepository,
            OttRepository ottRepository,
            SearchHistoryRepository searchHistoryRepository,
            UserRepository userRepository
    ) {
        this.contentsRepository = contentsRepository;
        this.genreRepository = genreRepository;
        this.ottRepository = ottRepository;
        this.searchHistoryRepository = searchHistoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public ContentsDTO findById(String id) {
        return ContentsDTO.from(
                this.contentsRepository.findById(id).orElseThrow(
                        () -> new BadRequestException(
                                ErrorCode.ROW_DOES_NOT_EXIST,
                                String.format("There are no data matches with contents id : %s", id
                                ))
                )
        );
    }

    @Transactional
    public void createSearchHistory(
            String contentsId,
            String keyword,
            String currentUserId
    ) {
        Contents contents = contentsRepository.findById(contentsId).orElseThrow(
                () -> new BadRequestException(
                        ErrorCode.ROW_DOES_NOT_EXIST,
                        String.format("There are no data matches with contents id : %s", contentsId
                        )
                )
        );
        User user = userRepository.findById(currentUserId).orElseThrow(
                () -> new BadRequestException(
                        ErrorCode.ROW_DOES_NOT_EXIST,
                        String.format("There is no user matches with user id : %s", currentUserId
                        )
                )
        );
        searchHistoryRepository.save(SearchHistory.of(contents, keyword, user));
    }
}
