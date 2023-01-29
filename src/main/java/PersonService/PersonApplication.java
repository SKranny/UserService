package PersonService;

import aws.annotation.EnableAwsClient;
import feignClient.EnableFeignClient;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import security.EnableMicroserviceSecurity;

@EnableCaching
@EnableAwsClient
@EnableFeignClient
@EnableEurekaClient
@SpringBootApplication
@EnableMicroserviceSecurity
public class PersonApplication {
	public static void main(String[] args) {
		SpringApplication.run(PersonApplication.class, args);
	}
}