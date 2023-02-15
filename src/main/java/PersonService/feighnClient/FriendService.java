package PersonService.feighnClient;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("friend-service/api/v1/friends/")
public interface FriendService {

    @GetMapping("/ids")
    @Operation(summary = "Получение id друзей")
    public List<Long> getFriendId();
}
