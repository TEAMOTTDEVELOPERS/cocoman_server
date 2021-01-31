package orangetaxiteam.cocoman.application;

import orangetaxiteam.cocoman.application.dto.PartyCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.PartyDTO;
import orangetaxiteam.cocoman.domain.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PartyApplicationService {

    private PartyService partyService;

    @Autowired
    public PartyApplicationService(PartyService partyService) {
        this.partyService = partyService;
    }

    @Transactional
    public PartyDTO create(PartyCreateRequestDTO partyCreateRequestDTO) {
        return PartyDTO.from(partyService.create(
                partyCreateRequestDTO.getOwnerId(),
                partyCreateRequestDTO.getPartyName(),
                partyCreateRequestDTO.getOtt(),
                partyCreateRequestDTO.getPrice(),
                partyCreateRequestDTO.getPayDay(),
                partyCreateRequestDTO.getMaxMember(),
                partyCreateRequestDTO.getStatus()
        ));
    }

}
