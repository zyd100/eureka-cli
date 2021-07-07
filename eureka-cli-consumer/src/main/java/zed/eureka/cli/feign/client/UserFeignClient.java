package zed.eureka.cli.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import zed.eureka.cli.entity.UserDto;
import zed.eureka.cli.feign.fallback.UserFeignFallbackFactory;

@Service
@FeignClient(name = "eureka-cli-provider-user", fallbackFactory = UserFeignFallbackFactory.class)
public interface UserFeignClient {

    @ResponseBody
    @GetMapping("{id}")
    UserDto getUser(@PathVariable("id") Integer id);
}
