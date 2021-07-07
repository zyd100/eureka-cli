package zed.eureka.cli.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import zed.eureka.cli.dto.UserDto;
import zed.eureka.cli.feign.client.UserFeignClient;

@RestController
public class MovieController {

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable(name = "id") String id) {
        return userFeignClient.getUser(Integer.parseInt(id));
    }


}

