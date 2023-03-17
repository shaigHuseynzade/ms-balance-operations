package az.azerconnect.sales.msbalanceoperations.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.boot.Metadata;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GenericResponseDto<T> {

    int code;
    String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    T data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Metadata metadata;

    private static final String MESSAGE_SUCCESS = "Success";
    private static final String MESSAGE_FAIL = "Fail";

    public static GenericResponseDto<Object> ofSuccess() {
        GenericResponseDto<Object> genericResponseDto = new GenericResponseDto<>();
        genericResponseDto.setCode(HttpStatus.OK.value());
        genericResponseDto.setMessage(MESSAGE_SUCCESS);
        return genericResponseDto;
    }

    public static <T> GenericResponseDto<T> ofSuccess(T data) {
        GenericResponseDto<T> genericResponseDto = new GenericResponseDto<>();
        genericResponseDto.setCode(HttpStatus.OK.value());
        genericResponseDto.setMessage(MESSAGE_SUCCESS);
        genericResponseDto.setData(data);
        return genericResponseDto;
    }

    public static <T> GenericResponseDto<T> ofException(int code,T data) {
        GenericResponseDto<T> genericResponseDto = new GenericResponseDto<>();
        genericResponseDto.setCode(code);
        genericResponseDto.setMessage(MESSAGE_FAIL);
        genericResponseDto.setData(data);
        return genericResponseDto;
    }

    public static <T> GenericResponseDto<T> ofSuccess(T data, Metadata metadata) {
        GenericResponseDto<T> genericResponseDto = new GenericResponseDto<>();
        genericResponseDto.setCode(HttpStatus.OK.value());
        genericResponseDto.setMessage(MESSAGE_SUCCESS);
        genericResponseDto.setData(data);
        genericResponseDto.setMetadata(metadata);
        return genericResponseDto;
    }

}
