package orangetaxiteam.cocoman.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_INITIAL_REVIEW")
public class InitialReview {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Double score;

    @ManyToOne
    @JoinTable(
            name = "initial_review_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "initial_review_id"))
    private User user;

    @ManyToOne
    @JoinTable(
            name = "initial_review_contents",
            joinColumns = @JoinColumn(name = "contents_id"),
            inverseJoinColumns = @JoinColumn(name = "initial_review_id"))
    private Contents contents;

    @Builder
    public InitialReview(Double score, User user, Contents contents) {
        this.score = score;
        this.user = user;
        this.contents = contents;
    }
}
