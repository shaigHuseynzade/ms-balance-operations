package az.azerconnect.sales.msbalanceoperations.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Slf4j
public class TelegramBot {

    private static HttpClient httpClient;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final String TELEGRAM_SEND_MESSAGE_URL = "https://api.telegram.org/bot%s/sendMessage";

    public static void sendMessage(String result) {
        try {
            String url = String.format(TELEGRAM_SEND_MESSAGE_URL, "5820379189:AAFDcC3OLqYGTWbhlDHTj_o30cTwnADZC6o");
            HttpClient httpClient = getHttpRequest();
            Map<String, String> params = new HashMap<>();
            params.put("chat_id", "-1001636948073");
            params.put("text", result);
            HttpRequest httpRequest = buildHttpRequest(url, params);
            httpClient.sendAsync(httpRequest,
                    HttpResponse.BodyHandlers.ofString());
        } catch (Exception ex) {
            log.error("Error happened while sending logs to telegram", ex);
        }
    }


    private static HttpClient getHttpRequest() {
        if (httpClient == null) {
            return httpClient = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_2)
                    .build();
        }
        return httpClient;
    }

    private static HttpRequest buildHttpRequest(String uri,Map<String, String> bodyParams) throws JsonProcessingException {
        String requestBody = null;
        if (Objects.nonNull(bodyParams)) {
            requestBody = objectMapper
                    .writeValueAsString(bodyParams);
        }
        return HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json")
                .POST(Objects.isNull(requestBody) ? HttpRequest.BodyPublishers.noBody() :
                        HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
    }
}
