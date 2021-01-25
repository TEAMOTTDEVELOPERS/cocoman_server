package orangetaxiteam.cocoman.application;

import orangetaxiteam.cocoman.application.dto.PartyDTO;
import orangetaxiteam.cocoman.domain.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartyApplicationService {
    @Autowired
    PartyService partyService;

    public PartyDTO create(PartyDTO partyDTO){
        return PartyDTO.toDto(partyService.create(partyDTO.getPartyName(), partyDTO.getOwnerId()));
    }

}
