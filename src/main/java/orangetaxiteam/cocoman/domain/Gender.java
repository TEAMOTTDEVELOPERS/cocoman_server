package orangetaxiteam.cocoman.domain;

import lombok.Getter;
import orangetaxiteam.cocoman.domain.exceptions.BadRequestException;
import orangetaxiteam.cocoman.domain.exceptions.ErrorCode;

import java.util.Arrays;

@Getter
public enum Gender {
    MALE("male"), FEMALE("female");

    private String value;

    Gender(String value) {
        this.value = value;
    }

    public static Gender of(String value) {
        Arrays.stream(Gender.values())
                .map(v -> v.value)
                .filter(v->v.equalsIgnoreCase(value))
                .findAny()
                .orElseThrow(() -> new BadRequestException(ErrorCode.PARAMETER_FORMAT_ERROR, String.format("Invalid parameter format - [Gender] : [%s]", value)));
        return Gender.valueOf(value.toLowerCase());
    }
}
