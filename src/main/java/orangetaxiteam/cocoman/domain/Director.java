package orangetaxiteam.cocoman.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_DIRECTOR")
public class Director {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String name;

    @Column (name = "image_path")
    private String imagePath;

    @ManyToMany (mappedBy = "directorSet", cascade = CascadeType.ALL)
    private Set<Contents> contentsSet;

    @Builder
    public Director(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }
}
