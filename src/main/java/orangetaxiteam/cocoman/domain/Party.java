package orangetaxiteam.cocoman.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_PARTY")
public class Party {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partyId;

    @Column(nullable = false, length = 100)
    private String partyName;

    @Column(nullable = false)
    private Long ownerId;

    @Column
    private String ott;

    @Column
    private Double price;

    @Column
    private Date payDay;    // 매월 결제하는 거니까 일만 입력..?

    @Column
    private Integer maxMember;

    @Temporal(TemporalType.DATE)
    @Column
    private Date startDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "enum('ACTIVATE','DEACTIVATE','EXPIRED')")
    private PartyStatus status;

    @Builder
    public Party (String partyName, Long ownerId){
        this.partyName = partyName;
        this.ownerId = ownerId;
    }


}
