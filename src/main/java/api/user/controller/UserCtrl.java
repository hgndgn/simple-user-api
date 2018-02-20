package api.user.controller;

import api.user.model.User;
import api.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
public class UserCtrl {

    @Autowired
    private UserService userService;

    @RequestMapping
    public List<User> getAllUsers() {
        return this.userService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable final Integer id) {
        return this.userService.get(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addUser(@RequestBody final User user) {
        this.userService.save(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public void updateUser(@RequestBody final User user) {
        this.userService.save(user);
    }

    @DeleteMapping(value = "/{id}")
    public void addUser(@PathVariable final Integer id) {
        this.userService.delete(id);
    }
}
