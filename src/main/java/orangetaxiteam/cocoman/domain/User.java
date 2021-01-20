package orangetaxiteam.cocoman.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Builder
@Table(name = "TB_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length=100, nullable=false)
    private String username;
    
    @Column(length=100, nullable=false)
    private String password;
 
    @Column(nullable=false)
    private String role; //authority
    
    @Column(nullable=false)
    private int age;

    @Column(length=6, nullable=false)
    private String gender;
    
    @Column
    private String phonenum;
    
    @Column(name = "profile_img")
    private String profileImg;
    
    //Column for app push alarm
    @Column
    private String pushtoken;

}
