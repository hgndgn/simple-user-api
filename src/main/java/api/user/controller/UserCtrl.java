package api.user.controller;

import api.user.model.User;
import api.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserCtrl {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String greeting() {
        return "Hello!\nGo to '/users' to see all users\n";
    }

    @RequestMapping("/users")
    public List<User> getAllUsers() {
        return this.userService.getAll();
    }
}
