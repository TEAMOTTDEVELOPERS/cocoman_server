package orangetaxiteam.cocoman.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TB_REVIEW")
public class Review extends DomainEntity {
    @Column(length = 500)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "contents_id")
    private Contents contents;

    private Review(String comment, User user, Contents contents) {
        this.comment = comment;
        this.user = user;
        this.contents = contents;
    }

    public static Review of(String comment, User user, Contents contents) {
        return new Review(comment, user, contents);
    }
}
