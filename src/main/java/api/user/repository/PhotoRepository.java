package api.user.repository;

import api.user.model.UserPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<UserPhoto, Integer> {
}
