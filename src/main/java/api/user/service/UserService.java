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

    public User get(Integer id) {
        return this.userRepository.findOne(id);
    }

    public void save(User user) {
        this.userRepository.save(user);
    }

    public void delete(Integer id) {
        this.userRepository.delete(id);
    }
}
