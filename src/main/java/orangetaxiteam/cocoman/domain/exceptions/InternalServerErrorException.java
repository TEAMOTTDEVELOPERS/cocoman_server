package orangetaxiteam.cocoman.domain.exceptions;

public class InternalServerErrorException extends CocomanException {
    public InternalServerErrorException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
