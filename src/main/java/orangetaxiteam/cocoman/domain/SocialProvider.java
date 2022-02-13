package orangetaxiteam.cocoman.domain;

import orangetaxiteam.cocoman.domain.exceptions.BadRequestException;
import orangetaxiteam.cocoman.domain.exceptions.ErrorCode;

import java.util.Arrays;

public enum SocialProvider {
    COCONUT("COCONUT"),
    GOOGLE("GOOGLE"),
    NAVER("NAVER"),
    KAKAO("KAKAO");

    private final String value;

    SocialProvider(String value) {
        this.value = value;
    }

    public static SocialProvider of(String value) {
        Arrays.stream(SocialProvider.values())
                .map(v -> v.value)
                .filter(v -> v.equalsIgnoreCase(value))
                .findAny()
                .orElseThrow(() -> new BadRequestException(ErrorCode.PARAMETER_FORMAT_ERROR, String.format("Invalid parameter format - [Social Provider] : [%s]", value)));
        return SocialProvider.valueOf(value.toUpperCase());
    }
}
