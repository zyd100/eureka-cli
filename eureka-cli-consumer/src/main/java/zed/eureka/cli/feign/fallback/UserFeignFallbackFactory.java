package zed.eureka.cli.feign.fallback;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import zed.eureka.cli.entity.UserDto;
import zed.eureka.cli.feign.client.UserFeignClient;

@Slf4j
@Component
public class UserFeignFallbackFactory implements FallbackFactory<UserFeignClient> {
    @Override
    public UserFeignClient create(Throwable throwable) {
        log.error(throwable.getMessage());
        return new UserFeignClient() {
            @Override
            public UserDto getUser(Integer id) {
                UserDto userDto = new UserDto();
                userDto.setId(id);
                userDto.setUsername("Error");
                return userDto;
            }
        };
    }
}
