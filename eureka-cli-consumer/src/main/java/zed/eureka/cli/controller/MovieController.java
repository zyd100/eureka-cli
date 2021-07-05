package zed.eureka.cli.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {



    @GetMapping("movie/user/{id}")
    public String findById(@PathVariable(name = "id") String id) {
        return"hello";
    }


}

