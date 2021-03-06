package api.user.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.io.IOException;
import java.util.Arrays;

@Entity
@Table(name = "USER_")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true, updatable = false, nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column
    private String password;

    @Lob
    @Column(name = "PHOTO")
    private byte[] photo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Id: " + id +
                "\nUsername: " + username +
                "\nEmail: " + email +
                "\nPassword: " + password +
                "\nPhoto: " + Arrays.toString(photo);
    }

    public static User parse(String from, User to) throws IOException {
        User u = new ObjectMapper().readValue(from, User.class);
        to.setUsername(u.getUsername());
        to.setEmail(u.getEmail());
        to.setPassword(u.getPassword());
        return to;
    }
}
