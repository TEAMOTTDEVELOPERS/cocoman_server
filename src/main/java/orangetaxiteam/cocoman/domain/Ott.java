package orangetaxiteam.cocoman.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TB_OTT")
public class Ott extends DomainEntity {
    @Column
    private String name;

    @Column(name = "image_path")
    private String imagePath;

    private Ott(
            String id,
            String name,
            String imagePath
    ) {
        super(id);
        this.name = name;
        this.imagePath = imagePath;
    }

    public static Ott of(
            String name,
            String imagePath
    ) {
        return new Ott(
                null,
                name,
                imagePath
        );
    }
}
