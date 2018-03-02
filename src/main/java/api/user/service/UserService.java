package api.user.service;

import api.user.model.User;
import api.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    public boolean checkUserExistence(String username) {
        return this.userRepository.checkUserExistence(username) != null;
    }

    public User getByUsername(String username) {
        return this.userRepository.findUserByUsername(username);
    }

    public void save(User user) {
        this.userRepository.save(user);
    }

    public void deleteByUsername(String username) {
        this.userRepository.deleteUserByUsername(username);
    }
}
