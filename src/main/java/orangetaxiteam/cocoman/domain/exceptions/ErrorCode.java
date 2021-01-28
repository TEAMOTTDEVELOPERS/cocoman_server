package orangetaxiteam.cocoman.domain.exceptions;

import lombok.Getter;

@Getter
public enum ErrorCode {
    SIGNIN_ID_DOES_NOT_EXIST(4000),
    SIGNIN_PASSWORD_DOES_NOT_MATCH(4001),
    PARAMETER_VALIDATION_ERROR(4002),
    PARAMETER_FORMAT_ERROR(4003),
    NOT_MATCHED_PARAMETER(4004),
    ID_DOES_NOT_EXIST(4005);

    private int code;

    ErrorCode(int code) {
        this.code = code;
    }
}
