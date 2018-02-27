package api.user.controller;

import api.user.model.User;
import api.user.model.UserPhoto;
import api.user.repository.PhotoRepository;
import api.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public void add(String jsonUser, @RequestParam("file") MultipartFile file) throws IOException {
        User user = new ObjectMapper().readValue(jsonUser, User.class);
        UserPhoto photo = new UserPhoto();

        photo.setData(file.getBytes());
        photo.setType(file.getContentType());
        photo = this.photoRepository.save(photo);
        user.setPhoto(photo);
        this.userService.save(user);
    }

    @RequestMapping(value = "/{jsonUser.username}", method = RequestMethod.PUT)
    public void update(@RequestPart("jsonUser") String jsonUser, @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        String username = new ObjectMapper().readValue(jsonUser, User.class).getUsername();
        User loadUser = this.userService.getByUsername(username);

        User parsedUser = User.parse(jsonUser, loadUser);
        if (file != null) {
            final Integer photoId = parsedUser.getPhoto().getId();
            UserPhoto photo = this.photoRepository.findOne(photoId);
            photo.setType(file.getContentType());
            photo.setData(file.getBytes());
            photo = this.photoRepository.save(photo);
            parsedUser.setPhoto(photo);
        }
        this.userService.save(parsedUser);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
    public void deleteUserByUsername(@PathVariable String username) {
        this.userService.deleteByUsername(username);
    }
}
