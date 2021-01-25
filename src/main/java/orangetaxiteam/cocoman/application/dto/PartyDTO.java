package orangetaxiteam.cocoman.application.dto;

import lombok.Getter;
import orangetaxiteam.cocoman.domain.Party;
import orangetaxiteam.cocoman.domain.PartyStatus;

import java.util.Date;

@Getter
public class PartyDTO {
    private long partyId, ownerId;
    private String partyName, ott;
    private double price;
    private Date payDay;
    private int maxMember;
    private Date startDate;
    private PartyStatus status;

    public static PartyDTO toDto(Party party){
        PartyDTO v = new PartyDTO();
        v.partyId = party.getPartyId();
        v.ownerId = party.getOwnerId();
        v.partyName = party.getPartyName();
        v.ott = party.getOtt();
        v.price = party.getPrice();
        v.payDay = party.getPayDay();
        v.maxMember = party.getMaxMember();
        v.startDate = party.getStartDate();
        v.status = party.getStatus();
        return v;
    }
}
