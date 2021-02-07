package orangetaxiteam.cocoman.domain.exceptions;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {
    private ErrorCode errorCode;
    private String message;

    public BadRequestException(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
