package api.user.controller;

import api.user.model.User;
import api.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserCtrl {

    @Autowired
    private UserService userService;

    @RequestMapping
    public List<User> getAllUsers() {
        return this.userService.getAll();
    }

    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable final Integer id) {
        return this.userService.getById(id);
    }

    @GetMapping(value = "/{username}", produces = "application/json")
    public User getUserByUsername(@PathVariable String username) {
        User u = this.userService.getByUsername(username);
        return u;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addUser(@RequestBody final User user) {
        this.userService.save(user);
    }

    @RequestMapping(value = "/{user.id}", method = RequestMethod.PUT, consumes = "application/json")
    public void updateUserById(@RequestBody final User user) {
        this.userService.save(user);
    }

    @RequestMapping(value = "/{user.username}", method = RequestMethod.PUT)
    public void updateUserByUsername(@RequestBody final User user) {
        this.userService.save(user);
    }

    @DeleteMapping(value = "/{id}")
    public void addUser(@PathVariable final Integer id) {
        this.userService.delete(id);
    }
}
