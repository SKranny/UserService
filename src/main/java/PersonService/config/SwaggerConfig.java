package PersonService.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
             .info(
                  new Info()
                       .description("Описание всех методов для микросервиса person-service")
                       .title("Api для person-service")
                       .version("1.0.0")
             );
    }
}
