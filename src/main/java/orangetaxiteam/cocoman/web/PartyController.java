package orangetaxiteam.cocoman.web;

import io.swagger.annotations.ApiOperation;
import orangetaxiteam.cocoman.application.PartyApplicationService;
import orangetaxiteam.cocoman.application.dto.PartyCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.PartyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/party")
public class PartyController {
    private PartyApplicationService partyApplicationService;

    @Autowired
    public PartyController(PartyApplicationService partyApplicationService){
        this.partyApplicationService = partyApplicationService;
    }

    @PostMapping
    public @ResponseBody
    PartyDTO createParty(@RequestBody PartyCreateRequestDTO partyCreateRequestDTO){
        return partyApplicationService.create(partyCreateRequestDTO);
    }


}
