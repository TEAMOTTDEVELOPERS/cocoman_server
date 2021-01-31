package orangetaxiteam.cocoman.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_REVIEW")
@EntityListeners(AuditingEntityListener.class)
public class Review {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id", unique = true)
    private String id;

    @Column
    private Double score;

    @Column (length = 500)
    private String comment;

    @CreatedDate
    @Column (name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column (name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinTable(
            name = "review_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "review_id"))
    private User user;

    @ManyToOne
    @JoinTable(
            name = "review_contents",
            joinColumns = @JoinColumn(name = "contents_id"),
            inverseJoinColumns = @JoinColumn(name = "review_id"))
    private Contents contents;

    @Builder
    public Review(Double score, String comment, User user, Contents contents) {
        this.score = score;
        this.comment = comment;
        this.user = user;
        this.contents = contents;
    }
}
