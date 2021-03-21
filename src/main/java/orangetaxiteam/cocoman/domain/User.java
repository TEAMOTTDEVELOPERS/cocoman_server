package orangetaxiteam.cocoman.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_USER")
public class User extends DomainEntity {
    @Column(name = "user_id")
    private String userId;

    @Column(length = 100, nullable = false, unique = true)
    private String nickName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(length = 100)
    private String password;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "phone_num")
    private String phoneNum;

    @Column(name = "profile_img")
    private String profileImg;

    //Column for app push alarm
    @Column(name = "push_token")
    private String pushToken;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Review> reviewSet;

    private User(
            String userId,
            String nickName,
            String password,
            Integer age,
            Gender gender,
            String phoneNum,
            String profileImg,
            String pushToken
    ) {
        this.userId = userId;
        this.nickName = nickName;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.phoneNum = phoneNum;
        this.profileImg = profileImg;
        this.pushToken = pushToken;
    }

    public static User of(
            String userId,
            String nickName,
            String password,
            Integer age,
            Gender gender,
            String phoneNum,
            String profileImg,
            String pushToken
    ) {
        return new User(userId, nickName, password, age, gender, phoneNum, profileImg, pushToken);
    }

    public static User social(
            String socialId,
            String nickName,
            Integer age,
            Gender gender,
            String phoneNum,
            String profileImg,
            String pushToken
    ) {
        return new User(
                socialId,
                nickName,
                null,
                age,
                gender,
                phoneNum,
                profileImg,
                pushToken
        );
    }

    public void update(int age, String gender, String phoneNum, String profileImg) {
        this.age = age;
        this.gender = Gender.of(gender);
        this.phoneNum = phoneNum;
        this.profileImg = profileImg;
    }

}
