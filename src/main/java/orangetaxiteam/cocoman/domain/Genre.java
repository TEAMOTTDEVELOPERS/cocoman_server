package orangetaxiteam.cocoman.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_GENRE")
@EntityListeners(AuditingEntityListener.class)
public class Genre {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id", unique = true)
    private String id;

    @Column (nullable = false)
    private String name;

    @CreatedDate
    @Column (name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column (name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToMany (mappedBy = "genreSet", cascade = CascadeType.ALL)
    private Set<Contents> contentsSet;

    @Builder
    public Genre(String name) {
        this.name = name;
    }
}
