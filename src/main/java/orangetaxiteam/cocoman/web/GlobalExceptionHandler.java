package orangetaxiteam.cocoman.web;

import lombok.extern.slf4j.Slf4j;
import orangetaxiteam.cocoman.domain.exceptions.BadRequestException;
import orangetaxiteam.cocoman.domain.exceptions.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Component
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDTO handleBadRequestException(BadRequestException exception) {
        GlobalExceptionHandler.log.error("error message", exception);
        return new ExceptionDTO(exception.getErrorCode(), exception.getMessage());
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionDTO handleAccessDeniedException(AccessDeniedException exception) {
        GlobalExceptionHandler.log.error("error message", exception);
        return new ExceptionDTO(ErrorCode.NOT_ALLOWED_ACCESS, exception.getMessage());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDTO handleNotValidException(MethodArgumentNotValidException exception) {
        GlobalExceptionHandler.log.error("error message", exception);
        String field = exception.getBindingResult().getFieldError().getField();
        String defaultMessage = exception.getBindingResult().getFieldError().getDefaultMessage();
        return new ExceptionDTO(ErrorCode.PARAMETER_FORMAT_ERROR, String.format("[%s] - %s", field, defaultMessage));
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDTO unknownException(Exception exception) {
        GlobalExceptionHandler.log.error("error message", exception);
        return new ExceptionDTO(ErrorCode.INTERNAL_SERVER, "internal server error");
    }
}
