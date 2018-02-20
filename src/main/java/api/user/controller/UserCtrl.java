package api.user.controller;

import api.user.model.User;
import api.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable final Integer id) {
        return this.userService.get(id);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public void addUser(@RequestBody final User user) {
        this.userService.save(user);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
    public void updateUser(@RequestBody final User user) {
        this.userService.save(user);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public void addUser(@PathVariable final Integer id) {
        this.userService.delete(id);
    }
}
