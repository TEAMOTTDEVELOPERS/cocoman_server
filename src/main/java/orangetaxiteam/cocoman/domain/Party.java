package orangetaxiteam.cocoman.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_PARTY")
@EntityListeners(AuditingEntityListener.class)
public class Party {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id", unique = true)
    private String id;

    @Column(nullable = false, length = 100)
    private String partyName;

    // TODO : FK
    @Column(nullable = false)
    private String ownerId;

    // TODO
    @Column
    private String ott;

    @Column
    private Double price;

    @Column
    private Date payDay;    // 매월 결제하는 거니까 일만 입력..?

    @Column
    private Integer maxMember;

    //, columnDefinition = "enum('ACTIVATE','DEACTIVATE','EXPIRED')"
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PartyStatus status;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    private Party(String ownerId, String partyName, String ott, Double price, Date payDay, Integer maxMember, PartyStatus status) {
        this.ownerId = ownerId;
        this.partyName = partyName;
        this.ott = ott;
        this.price = price;
        this.payDay = payDay;
        this.maxMember = maxMember;
        this.status = status;
    }

    public static Party of(String ownerId, String partyName, String ott, Double price, Date payDay, Integer maxMember, PartyStatus status) {
        return new Party(ownerId, partyName, ott, price, payDay, maxMember, status);
    }
}
