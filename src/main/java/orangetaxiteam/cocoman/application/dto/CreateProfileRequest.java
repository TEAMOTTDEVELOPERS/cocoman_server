package orangetaxiteam.cocoman.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateProfileRequest {
    private String name;
    private Boolean isKid;
    private String imageBinary;
}
