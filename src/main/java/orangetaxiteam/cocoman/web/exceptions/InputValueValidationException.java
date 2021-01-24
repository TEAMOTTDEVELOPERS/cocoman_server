package orangetaxiteam.cocoman.web.exceptions;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputValueValidationException extends RuntimeException {
    private List<Object> params = new ArrayList<>();

    public InputValueValidationException(Object... params) {
        this.params.addAll(Arrays.asList(params));
    }

    public ExceptionDTO getResponse() {
        String message = "Inadequate parameters: ";
        for (Object obj : params) message += obj.toString();
        return new ExceptionDTO(HttpStatus.BAD_REQUEST.value(), "Input value bad request", message);
    }
}
