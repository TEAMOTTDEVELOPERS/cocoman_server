package orangetaxiteam.cocoman.domain.exceptions;

import lombok.Getter;

@Getter
public abstract class CocomanException extends RuntimeException {
    private ErrorCode errorCode;
    private String message;

    public CocomanException(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
