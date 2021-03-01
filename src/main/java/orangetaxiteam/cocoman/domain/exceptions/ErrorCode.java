package orangetaxiteam.cocoman.domain.exceptions;

import lombok.Getter;

@Getter
public enum ErrorCode {
    ROW_DOES_NOT_EXIST(4000),
    ROW_ALREADY_EXIST(4001),
    ESSENTIAL_PARAETER_IS_NULL(4002),
    PARAMETER_FORMAT_ERROR(4003),
    ID_ALREADY_EXIST(4004),

    SIGNIN_PASSWORD_DOES_NOT_MATCH(4100),
    TOKEN_IS_EXPIRED(4101),
    NOT_ALLOWED_ACCESS(4102),

    INTERNAL_SERVER(5000);

    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }
}
