package orangetaxiteam.cocoman.web;

import orangetaxiteam.cocoman.application.PartyApplicationService;
import orangetaxiteam.cocoman.application.dto.PartyCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.PartyDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/parties")
public class PartyController {
    private final PartyApplicationService partyApplicationService;

    public PartyController(PartyApplicationService partyApplicationService) {
        this.partyApplicationService = partyApplicationService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public PartyDTO createParty(@RequestBody PartyCreateRequestDTO partyCreateRequestDTO) {
        return this.partyApplicationService.create(partyCreateRequestDTO);
    }
}
