package orangetaxiteam.cocoman.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TB_OTT")
public class Ott extends DomainEntity {
    @Column
    private String name;

    @Column(name = "image_path")
    private String imagePath;

    @ManyToMany
    @JoinColumn(name = "contents")
    private Set<Contents> contentsSet;

    private Ott(
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

    public static Ott of(
            String name,
            String imagePath,
            Set<Contents> contentsSet
    ) {
        return new Ott(
                null,
                name,
                imagePath,
                contentsSet
        );
    }
}
