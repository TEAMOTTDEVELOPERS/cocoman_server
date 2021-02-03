package orangetaxiteam.cocoman.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_USER_PROFILE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProfile {
    @Id
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @Column
    private String name;
    @Column
    private Boolean isKid;
    @Column
    private String imagePath;

    private UserProfile(
            String name,
            Boolean isKid,
            String imagePath
    ) {
        this.name = name;
        this.isKid = isKid;
        this.imagePath = imagePath;
    }

    public static UserProfile of(String name, Boolean isKid, String profileImagePath) {
        return new UserProfile(name, isKid, profileImagePath);
    }
}
