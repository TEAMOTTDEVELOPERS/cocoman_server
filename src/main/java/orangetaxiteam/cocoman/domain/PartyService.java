package orangetaxiteam.cocoman.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class PartyService {
    private final PartyRepository partyRepository;

    @Autowired
    public PartyService(PartyRepository partyRepository){
        this.partyRepository = partyRepository;
    }

    private void validateDuplicatePartyName(Party party) {
        partyRepository.findByPartyName(party.getPartyName())
                .ifPresent(m->{
                    throw  new IllegalStateException("이미 존재하는 파티 이름");
                });
    }

    public List<Party> findAll() {
        return partyRepository.findAll();
    }

    public Party create(String partyName, Long ownerId) {
        return partyRepository.save(new Party(partyName,ownerId));
    }

}
