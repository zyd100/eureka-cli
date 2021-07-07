package zed.eureka.cli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication(scanBasePackages = "zed")
public class EurekaCliProviderUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaCliProviderUserApplication.class, args);
    }

}
