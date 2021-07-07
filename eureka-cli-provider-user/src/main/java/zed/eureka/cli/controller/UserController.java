package zed.eureka.cli.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import zed.eureka.cli.entity.User;
import zed.eureka.cli.mapper.UserDao;


@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/{id}")
    public User findById(@PathVariable(name = "id") String id) {
        return userDao.selectById(id);
    }


}

