package az.azerconnect.sales.msbalanceoperations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class MsBalanceOperationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsBalanceOperationsApplication.class, args);
    }

}