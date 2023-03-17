package az.azerconnect.sales.msbalanceoperations.client.error;

import az.azerconnect.sales.msbalanceoperations.client.dto.GenericResponse;
import az.azerconnect.sales.msbalanceoperations.configuration.TelegramBot;
import az.azerconnect.sales.msbalanceoperations.constants.ErrorMessage;
import az.azerconnect.sales.msbalanceoperations.constants.HeaderName;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Slf4j
@Component
public class ClientErrorDecoder implements ErrorDecoder {


    @Override
    public Exception decode(String methodKey, Response response) {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = null;
        try {
            inputStream = response.body().asInputStream();
                String result = IOUtils.toString(inputStream);
            String trackId = "";
            if (response.headers().containsKey(HeaderName.TRACK_ID)) {
                trackId = response.headers().get(HeaderName.TRACK_ID).stream().findFirst().get();
            }
            TelegramBot.sendMessage(trackId.concat("->").concat(result));
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            GenericResponse<ErrorResponse> genericResponse = objectMapper.readValue(result, new TypeReference<GenericResponse<ErrorResponse>>() {
            });
            if (Objects.nonNull(genericResponse) && genericResponse.getData() != null) {
                throw new ClientException(response.status(), genericResponse.getData().getCode(), genericResponse.getData().getMessage(), trackId);
            } else {
                throw new ClientException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorMessage.INTERNAL_ERROR_OCCURRED.getCode(), ErrorMessage.INTERNAL_ERROR_OCCURRED.getMessage(), trackId);
            }
        } catch (IOException ex) {
            log.error("ClientErrorDecoder: ", ex);
            throw new ClientException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorMessage.INTERNAL_ERROR_OCCURRED.getCode(), ErrorMessage.INTERNAL_ERROR_OCCURRED.getMessage());
        }
    }
}

