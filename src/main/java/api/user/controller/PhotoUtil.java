package api.user.controller;

import api.user.model.UserPhoto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class PhotoUtil {

    public static UserPhoto setPhotoData(MultipartFile file) {
        UserPhoto photo = new UserPhoto();
        try {
            photo.setData(file.getBytes());
        } catch (IOException e) {
            System.err.println("uploadPhoto(): error while setting data (bytes)");
            return null;
        }
        photo.setType(file.getContentType());
        return photo;
    }
}
