package api.user.controller;

import api.user.model.User;
import api.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserCtrl {

    @Autowired
    private UserService userService;


    @RequestMapping
    public List<User> getAll() {
        return this.userService.getAll();
    }

    @GetMapping(value = "/{username}", produces = "application/json")
    public User getByUsername(@PathVariable String username) {
        return this.userService.getByUsername(username);
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public void add(String jsonUser, @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            User user = new ObjectMapper().readValue(jsonUser, User.class);

            if (file == null) {
                final ClassPathResource res = new ClassPathResource("/image/default.png");
                user.setPhoto(Files.readAllBytes(res.getFile().toPath()));
            } else {
                user.setPhoto(file.getBytes());
            }
            this.userService.save(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/{jsonUser.username}", method = RequestMethod.PUT)
    public void update(@RequestPart("jsonUser") String jsonUser, @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            String username = new ObjectMapper().readValue(jsonUser, User.class).getUsername();
            User loadUser = this.userService.getByUsername(username);
            User parsedUser = User.parse(jsonUser, loadUser);

            if (file != null) {
                parsedUser.setPhoto(file.getBytes());
            }
            this.userService.save(parsedUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
    public void deleteUserByUsername(@PathVariable String username) {
        this.userService.deleteByUsername(username);
    }
}
