package orangetaxiteam.cocoman.domain.exceptions;

public class BadRequestException extends CocomanException {
    public BadRequestException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
