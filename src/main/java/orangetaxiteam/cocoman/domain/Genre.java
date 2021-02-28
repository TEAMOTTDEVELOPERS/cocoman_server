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
@Table(name = "TB_GENRE")
public class Genre extends DomainEntity {
    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "genreSet", cascade = CascadeType.ALL)
    private Set<Contents> contentsSet;

    private Genre(
            String id,
            String name,
            Set<Contents> contentsSet
    ) {
        super(id);
        this.name = name;
        this.contentsSet = contentsSet;
    }

    public static Genre of(String name) {
        return new Genre(
                null,
                name,
                null
        );
    }
}
