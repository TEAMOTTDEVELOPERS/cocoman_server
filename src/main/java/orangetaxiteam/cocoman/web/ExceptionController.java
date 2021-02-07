package orangetaxiteam.cocoman.web;

import orangetaxiteam.cocoman.application.dto.ExceptionDTO;
import orangetaxiteam.cocoman.domain.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice("orangetaxiteam.cocoman.web")
public class ExceptionController {
    // Bad Request - 400
    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> BadRequestException(final BadRequestException exception) {
        return new ResponseEntity<>(new ExceptionDTO(exception.getErrorCode().getCode(), exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    // Access Denied - 401
    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity handleAccessDeniedException(final AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
}
