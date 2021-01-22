package orangetaxiteam.cocoman.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_ACTOR")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String name;

    @Column (name = "image_path")
    private String imagePath;

    @ManyToMany (mappedBy = "actorSet", cascade = CascadeType.ALL)
    Set<Contents> contentsSet;

    @Builder
    public Actor(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }
}
