package orangetaxiteam.cocoman.application.dto;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class ExceptionDTO {
    private String timestamp;
    private int status;
    private String error;
    private String message;

    public ExceptionDTO(int status, String error, String message) {
        this.timestamp = new Timestamp(System.currentTimeMillis()).toString();
        this.status = status;
        this.error = error;
        this.message = message;
    }
}
