package orangetaxiteam.cocoman.application.dto;

import lombok.Getter;

@Getter
public class ExceptionDTO {
    private Integer errorCode;
    private String message;

    public ExceptionDTO(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
