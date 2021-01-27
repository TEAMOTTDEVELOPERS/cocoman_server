package orangetaxiteam.cocoman.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_GENRE")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String name;

    @ManyToMany (mappedBy = "genreSet", cascade = CascadeType.ALL)
    private Set<Contents> contentsSet;

    @Builder
    public Genre(String name) {
        this.name = name;
    }
}
