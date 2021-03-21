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
public class ClickHistory extends DomainEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private User clickUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private Contents clickContents;

    @Column(name = "search_keyword")
    private String searchKeyword;

    private ClickHistory(
            Contents contents,
            String keyword,
            User user
    ) {
        this.clickContents = contents;
        this.searchKeyword = keyword;
        this.clickUser = user;
    }

    public static ClickHistory of(
            Contents contents,
            String keyword,
            User user
    ) {
        return new ClickHistory(contents, keyword, user);
    }
}
