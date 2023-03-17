package az.azerconnect.sales.msbalanceoperations.configuration;

import az.azerconnect.sales.msbalanceoperations.client.error.ClientErrorDecoder;
import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NgbssClientConfiguration {

    @Bean
    ErrorDecoder errorDecoderNgbss() {
        return new ClientErrorDecoder();
    }

    @Bean
    Logger.Level loggerLevelNgbss() {
        return Logger.Level.FULL;
    }
}
