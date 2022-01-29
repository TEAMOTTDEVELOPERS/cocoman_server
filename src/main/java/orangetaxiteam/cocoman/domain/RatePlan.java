package orangetaxiteam.cocoman.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TB_RATE_PLAN")
public class RatePlan extends DomainEntity {
    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String cost;

    @Column(length = 50)
    private String resolution;

    @Column
    private Integer maxSimultaneous;

    @ManyToOne
    @JoinColumn(name = "ott")
    private Ott ott;

    private RatePlan(String id, String name, String cost, String resolution, Integer maxSimultaneous, Ott ott) {
        super(id);
        this.name = name;
        this.cost = cost;
        this.resolution = resolution;
        this.maxSimultaneous = maxSimultaneous;
        this.ott = ott;
    }

    public static RatePlan of(String name, String cost, String resolution, Integer maxSimultaneous, Ott ott) {
        return new RatePlan(null, name, cost, resolution, maxSimultaneous, ott);
    }
}
