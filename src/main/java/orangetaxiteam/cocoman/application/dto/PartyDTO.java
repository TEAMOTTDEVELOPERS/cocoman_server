package orangetaxiteam.cocoman.application.dto;

import lombok.Getter;
import lombok.Setter;
import orangetaxiteam.cocoman.domain.Party;
import orangetaxiteam.cocoman.domain.PartyStatus;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class PartyDTO {
    private String partyId;
    private String ownerId;
    private String partyName, ott;
    private double price;
    private Date payDay;
    private int maxMember;
    private PartyStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static PartyDTO from(Party party){
        PartyDTO v = new PartyDTO();
        v.partyId = party.getId();
        v.ownerId = party.getOwnerId();
        v.partyName = party.getPartyName();
        v.ott = party.getOtt();
        v.price = party.getPrice();
        v.payDay = party.getPayDay();
        v.maxMember = party.getMaxMember();
        v.status = party.getStatus();
        v.createdAt = party.getCreatedAt();
        v.updatedAt = party.getUpdatedAt();
        return v;
    }
}
