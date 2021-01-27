package orangetaxiteam.cocoman.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_KEYWORD")
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToMany (mappedBy = "keywordSet", cascade = CascadeType.ALL)
    private Set<Contents> contentsSet;

    @Builder
    public Keyword(String name) {
        this.name = name;
    }
}
