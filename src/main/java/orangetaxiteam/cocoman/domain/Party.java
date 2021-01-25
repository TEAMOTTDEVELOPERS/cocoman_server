package orangetaxiteam.cocoman.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_PARTY")
public class Party {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partyId;

    @Column(nullable = false, length = 100)
    private String partyName;

    // TODO : FK
    @Column(nullable = false)
    private Long ownerId;

    // TODO
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

    //, columnDefinition = "enum('ACTIVATE','DEACTIVATE','EXPIRED')"
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PartyStatus status;

    @Builder
    public Party (Long ownerId, String partyName, String ott, Double price, Date payDay, Integer maxMember, Date startDate, PartyStatus status){
        this.ownerId = ownerId;
        this.partyName = partyName;
        this.ott = ott;
        this.price = price;
        this.payDay = payDay;
        this.maxMember = maxMember;
        this.startDate = startDate;
        this.status = status;
    }

}
