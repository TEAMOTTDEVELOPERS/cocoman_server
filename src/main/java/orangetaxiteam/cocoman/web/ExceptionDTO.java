package orangetaxiteam.cocoman.web;

import lombok.Getter;
import lombok.Setter;
import orangetaxiteam.cocoman.domain.exceptions.ErrorCode;

@Getter
@Setter
public class ExceptionDTO {
    private final Integer errorCode;
    private final String message;

    public ExceptionDTO(ErrorCode errorCode, String message) {
        this.errorCode = errorCode.getCode();
        this.message = message;
    }
}
