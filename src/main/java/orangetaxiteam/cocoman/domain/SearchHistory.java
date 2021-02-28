package orangetaxiteam.cocoman.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TB_SEARCH_HISTORY")
@EntityListeners(AuditingEntityListener.class)
public class SearchHistory {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "search_user")
    @ManyToOne(fetch = FetchType.LAZY)
    private User searchUser;

    @Column(name = "search_contents")
    @ManyToOne(fetch = FetchType.LAZY)
    private Contents searchContents;

    @Column(name = "search_keyword")
    private String searchKeyword;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    private SearchHistory(
            Contents contents,
            String keyword,
            User user
    ) {
        this.searchContents = contents;
        this.searchKeyword = keyword;
        this.searchUser = user;
    }

    public static SearchHistory of(
            Contents contents,
            String keyword,
            User user
    ) {
        return new SearchHistory(contents, keyword, user);
    }
}
