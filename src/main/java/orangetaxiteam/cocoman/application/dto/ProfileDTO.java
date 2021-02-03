package orangetaxiteam.cocoman.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import orangetaxiteam.cocoman.domain.UserProfile;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileDTO {
    private String id;
    private String name;
    private Boolean isKid;
    private String imageBinary;

    public static ProfileDTO of(UserProfile createdProfile, String imageBinary) {
        return new ProfileDTO(
                createdProfile.getId(),
                createdProfile.getName(),
                createdProfile.getIsKid(),
                imageBinary
        );
    }
}
