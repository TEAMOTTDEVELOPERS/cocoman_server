package orangetaxiteam.cocoman.application;

import orangetaxiteam.cocoman.application.dto.InitialReviewCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.InitialReviewDTO;
import orangetaxiteam.cocoman.domain.*;
import orangetaxiteam.cocoman.web.exceptions.InputValueValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitialReviewApplicationService {
    private InitialReviewService initialReviewService;
    private UserService userService;
    private ContentsService contentsService;

    @Autowired
    public InitialReviewApplicationService(InitialReviewService initialReviewService, UserService userService, ContentsService contentsService){
        this.initialReviewService = initialReviewService;
        this.userService = userService;
        this.contentsService = contentsService;
    }

    public InitialReviewDTO create(InitialReviewCreateRequestDTO initialReviewCreateRequestDTO){
        User user = userService.findById(initialReviewCreateRequestDTO.getUserId()).orElseThrow(
                () -> new InputValueValidationException("invalid user id")
        );

        Contents contents = contentsService.findById(initialReviewCreateRequestDTO.getContentsId()).orElseThrow(
                () -> new InputValueValidationException("invalid contents id")
        );


        return InitialReviewDTO.fromDAO(
                initialReviewService.create(
                        initialReviewCreateRequestDTO.getScore(),
                        user,
                        contents
                )
        );
    }
}
