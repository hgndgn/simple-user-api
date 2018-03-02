package api.user.controller;

import api.user.model.User;
import api.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserCtrl {

    private static final String IMAGE_PNG = "image/png";
    private static final String IMAGE_JPEG = "image/jpeg";

    @Autowired
    private UserService userService;

    @RequestMapping
    public List<User> getAll() {
        return this.userService.getAll();
    }

    @GetMapping(value = "/{username}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> getByUsername(@PathVariable String username) {
        User u = this.userService.getByUsername(username);
        if (u == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity add(String jsonUser, @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            User user = new ObjectMapper().readValue(jsonUser, User.class);

            if (this.userService.checkUserExistence(user.getUsername())) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }

            if (file == null) {
                final ClassPathResource res = new ClassPathResource("/image/default.png");
                user.setPhoto(Files.readAllBytes(res.getFile().toPath()));
            } else if (!checkImageType(file)) {
                return new ResponseEntity(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
            } else {
                user.setPhoto(file.getBytes());
            }

            this.userService.save(user);
            return new ResponseEntity(HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/{jsonUser.username}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity update(@RequestPart("jsonUser") String jsonUser, @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            String username = new ObjectMapper().readValue(jsonUser, User.class).getUsername();
            User loadUser = this.userService.getByUsername(username);
            User parsedUser = User.parse(jsonUser, loadUser);

            if (file != null) {
                if (!checkImageType(file)) {
                    return new ResponseEntity(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
                }
                parsedUser.setPhoto(file.getBytes());
            }

            this.userService.save(parsedUser);
            return new ResponseEntity(HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteUserByUsername(@PathVariable String username) {
        if (!this.userService.checkUserExistence(username)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        this.userService.deleteByUsername(username);
        return new ResponseEntity(HttpStatus.OK);
    }

    private boolean checkImageType(MultipartFile file) {
        return file.getContentType().equals(IMAGE_PNG) || file.getContentType().equals(IMAGE_JPEG);
    }
}