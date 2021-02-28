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
@Table(name = "TB_ACTOR")
public class Actor extends DomainEntity {
    @Column(nullable = false)
    private String name;

    @Column(name = "image_path")
    private String imagePath;

    @ManyToMany(mappedBy = "actorSet", cascade = CascadeType.ALL)
    private Set<Contents> contentsSet;

    private Actor(
            String id,
            String name,
            String imagePath,
            Set<Contents> contentsSet
    ) {
        super(id);
        this.name = name;
        this.imagePath = imagePath;
        this.contentsSet = contentsSet;
    }

    public static Actor of(String name, String imagePath) {
        return new Actor(
                null,
                name,
                imagePath,
                null
        );
    }
}
