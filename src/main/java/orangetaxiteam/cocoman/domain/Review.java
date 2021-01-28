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
@Table(name = "TB_REVIEW")
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Double score;

    @Column (length = 500)
    private String comment;

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
