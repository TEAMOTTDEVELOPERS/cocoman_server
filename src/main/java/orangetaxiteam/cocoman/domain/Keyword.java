package orangetaxiteam.cocoman.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TB_KEYWORD")
public class Keyword extends DomainEntity {
    @Column
    private String name;

    @ManyToMany(mappedBy = "keywordSet", cascade = CascadeType.ALL)
    private Set<Contents> contentsSet;

    private Keyword(
            String id,
            String name,
            Set<Contents> contentsSet
    ) {
        super(id);
        this.name = name;
        this.contentsSet = contentsSet;
    }

    public static Keyword of(String name) {
        return new Keyword(
                null,
                name,
                null
        );
    }
}
