package orangetaxiteam.cocoman.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import orangetaxiteam.cocoman.domain.PartyStatus;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartyCreateRequestDTO {
    private String ownerId;
    private String partyName;
    private String ott;
    private double price;
    private Date payDay;
    private int maxMember;
    private PartyStatus status;

}
