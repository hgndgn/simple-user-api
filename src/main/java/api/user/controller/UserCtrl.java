package api.user.controller;

import api.user.model.User;
import api.user.model.UserPhoto;
import api.user.repository.PhotoRepository;
import api.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserCtrl {

    @Autowired
    private UserService userService;

    @Autowired
    private PhotoRepository photoRepository;

    @RequestMapping
    public List<User> getAll() {
        return this.userService.getAll();
    }

    @GetMapping(value = "/{username}", produces = "application/json")
    public User getByUsername(@PathVariable String username) {
        return this.userService.getByUsername(username);
    }

    @RequestMapping(value="/add-user", method = RequestMethod.POST)
    public void add(@RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("file") MultipartFile file) {
        UserPhoto photo = PhotoUtil.setPhotoData(file);

        if (photo != null) {
            photo = this.photoRepository.save(photo);

            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPicture(photo);
            this.userService.save(user);
        }
    }

    @RequestMapping(value = "/{user.username}", method = RequestMethod.PUT, consumes = "application/json")
    public void updateUserByUsername(@RequestBody User user) {
        this.userService.save(user);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
    public void deleteUserByUsername(@PathVariable String username) {
        this.userService.deleteByUsername(username);
    }

    // also works
//    @DeleteMapping
//    public void deleteUserByUsername(@RequestParam("username") String username) {
//        this.userService.deleteByUsername(username);
//    }
}
