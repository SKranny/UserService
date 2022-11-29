package PersonService.feighnClient;

import PersonService.dto.LoginRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "authClient", url = "http://localhost:8081/api/v1/auth")
public interface AuthClient {
    @PostMapping("/login")
     String login(@RequestBody LoginRequest request);
}

