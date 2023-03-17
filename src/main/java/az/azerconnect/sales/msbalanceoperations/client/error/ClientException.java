package az.azerconnect.sales.msbalanceoperations.client.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientException extends RuntimeException{

    private int httpCode;
    private int code;
    private String message;
    private String trackId;

    public ClientException(int httpCode, int code, String message) {
        super(message);
        this.httpCode = httpCode;
        this.code = code;
        this.message = message;
    }

    public ClientException(int httpCode, int code, String message, String trackId) {
        super(message);
        this.httpCode = httpCode;
        this.code = code;
        this.message = message;
        this.trackId = trackId;
    }
}
