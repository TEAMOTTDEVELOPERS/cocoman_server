package orangetaxiteam.cocoman.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import orangetaxiteam.cocoman.domain.exception.BadRequestException;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_USER")
@EntityListeners(AuditingEntityListener.class)
public class User {
    private final static int MAXIMUM_PROFILE_COUNTS = 4;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id", unique = true)
    private String id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(length = 100, nullable = false, unique = true)
    private String nickName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(length = 100, nullable = false)
    private String password;

    @Column(nullable = false)
    private Integer age;

    @Column(length = 6, nullable = false)
    private String gender;

    @Column(name = "phone_num")
    private String phoneNum;

    @Column(name = "profile_img")
    private String profileImg;

    //Column for app push alarm
    @Column(name = "push_token")
    private String pushToken;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Review> reviewSet;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserProfile> userProfiles;

    @Builder
    public User(String userId, String nickName, String password, Integer age, String gender, String phoneNum, String profileImg, String pushToken) {
        this.userId = userId;
        this.nickName = nickName;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.phoneNum = phoneNum;
        this.profileImg = profileImg;
        this.pushToken = pushToken;
    }

    public UserProfile createProfile(String name, Boolean isKid, String profileImagePath) {
        if (!this.canCreateProfile()) {
            throw new BadRequestException(String.format("user '%s' can not create profile more than '%d'", this.id, MAXIMUM_PROFILE_COUNTS))
        }

        UserProfile userProfile = UserProfile.of(name, isKid, profileImagePath);
        this.userProfiles.add(userProfile);
        return userProfile;
    }

    private boolean canCreateProfile() {
        return this.userProfiles.size() < MAXIMUM_PROFILE_COUNTS;
    }
}
