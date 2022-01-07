package orangetaxiteam.cocoman.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TB_SEARCH_HISTORY")
public class SearchHistory extends DomainEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private User searchUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private Contents searchContents;

    @Column(name = "search_keyword")
    private String searchKeyword;

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
