package orangetaxiteam.cocoman.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import orangetaxiteam.cocoman.domain.SocialProvider;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignInDTO {
    private String userId;
    private String password;
    private String accessToken;

    @NotEmpty
    private String provider;
}
