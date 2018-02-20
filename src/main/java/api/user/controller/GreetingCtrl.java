package api.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingCtrl {

    @RequestMapping("/")
    public String greeting() {
        return "Hello!  Go to '/users' to see all users";
    }
}
