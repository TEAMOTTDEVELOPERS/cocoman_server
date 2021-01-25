package orangetaxiteam.cocoman.web;

import io.swagger.annotations.ApiOperation;
import orangetaxiteam.cocoman.application.PartyApplicationService;
import orangetaxiteam.cocoman.application.dto.PartyCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.PartyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/party")
public class PartyController {

    @Autowired
    private PartyApplicationService partyApplicationService;

    @PostMapping(value = "/createParty")
    @ApiOperation(value = "Create new party", tags = "Party")
    public @ResponseBody
    PartyDTO createParty(@RequestBody PartyCreateRequestDTO partyCreateRequestDTO){
        return partyApplicationService.create(partyCreateRequestDTO);
    }


}
